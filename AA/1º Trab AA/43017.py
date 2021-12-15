from sklearn.model_selection import train_test_split
import math
import copy
import numpy as np
import thrift

def atrDistMatrx(arr): # atributos distintos da matriz
        valores = {}
        aux =[]

        for x in range(0, len(arr[0])): # Percorre horizontalmente
            for y in range(0, len(arr)): #Percorre verticalmente
                if arr[y][x] not in aux:
                    aux.append(arr[y][x]) #Insere os valores     
                    
            valores[x]=tuple(aux) # Insere num dicionário tuplos com elementos únicos associados ao index da coluna
            aux.clear() #Limpa o array para inserirmos a próxima coluna

        return valores #Fazemos um sort para que todos os valores fiquem sempre no mesmo sitio e damos return

class myDecisionTreeREPrune:
    
    def __init__(self,criterion='gini',prune='True'):
        self.prune=prune
        self.criterion=criterion
        self.tree = None

    

    def calcImp(self, node , x, y): # calculo da impureza

        dic_classes = {} # Vai guardar todas as classes, e quantos exemplos pertencem a cada classe {yes: 2, no: 5}


        vPureza = 1 if self.criterion=="gini" else 0 # Inicializar vPureza consoante o critério

        for classe in y:
            dic_classes[classe]=0 # Inicializar o número de vezes que cada classe aparece a 0

        for exemplo in node.data:
 
            dic_classes[y[x.index(exemplo)]]+=1 # Procurar o indice na matriz X, onde os exemplos de dados_no
                                                # aparecem, depois ir a essa posição da lista y, e incrementar no dic_classes
                
        node.classe = max(dic_classes, key=dic_classes.get) # A classe do nó, passa a ser a que aparece mais vezes

        #Aqui, consoante o critério fazer a conta da pureza
        for classe in dic_classes:
            if dic_classes[classe]!=0:

                p = dic_classes[classe] / len(node.data) # Numero de vezes que cada classe aparece
                                                        # A dividir pelo número total
                if self.criterion == "entropia":

                    vPureza -= p*math.log(p,2) # sum(-p*log2(p))
                elif self.criterion == "gini":

                    vPureza -= p**2 # 1-sum(p^2)
                elif self.criterion == "erro":

                    vPureza+= min(p, 1-p) # sum(min(p,1-p)) ??

        return vPureza 

    def split(self, tree):
        
        raiz = tree.raiz
        x_train = raiz.data
        y_train = raiz.arrY
        

        if ( not raiz.isLeaf() ): # Se não for folha fazemos a recursão para todos os filhos            
            
            for index,filho in enumerate(raiz.filhos): # Percorrer todos os filhos e fazer o split dos mesmos recursivamente

                raiz.filhos[index] = self.split(DTree(filho)).raiz # Substituimos o filho recursivamnte 

        else: # Se for uma folha
            if not raiz.isFinished(): # Se o nó que estamos a trabalhar ainda pode fazer split

                dic_atributos = atrDistMatrx(x_train)       # {0: (a,b), 1: (suny,cloudy)}
                                                            # n = numero da coluna
                                                            # (tuplo) = atributos unicos associados a cada coluna

                dic_imp={}          # Guardar o indice da arvore na listaFinal e associar uma imp
                listaFinal=list()   # Vai conter todas as arvores criadas, no final devolve-se a que tem menos impureza

                # Calculamos a impureza da arvore sem split          
                dic_imp[len(listaFinal)]=self.calcImp(raiz,x_train,y_train) # É colocada a imp da arvore, utiliza-se o len(listaFinal) como contador
                listaFinal.append(tree)

                # Fazer o split de todas as maneiras possiveis e guardar cada arvore obtida na listaFinal
                for n_coluna in dic_atributos: # Percorrer cada coluna de atributos                    

                    if n_coluna not in raiz.splitFeitos: # Apenas calculamos para atributos que ainda não foram utilizados

                        tree_aux = copy.deepcopy(tree)
                        all_filhos =list() # Lista de todos os filhos
                        impTotal = 0 # De cada arvore
                        
                        for atributo in dic_atributos[n_coluna]: # Percorrer cada atributo dentro da coluna

                            filho = list() # exemplo onde o atributo aparece

                            for exemplo in x_train: #Percorrer a nossa tabela de atributos
        
                                if atributo in exemplo[n_coluna]: # Pegamos na linha que tem o atributo que estamos a dividir e colocamos num novo array

                                    filho.append(exemplo) #Aqui colocamos todos os exemplos onde o atributo aparece


                            #Neste momento já temos todos os dados que queremos colocar no nó em filho
                            novoY = self.actYconsoanteDadosNo(filho, x_train, y_train) # Calcula-se a tabela de Y associada aos exemplos deste nó
                            node_filho = Node(filho, novoY) # Cria-se um nó com os dados + pureza
                            node_filho.setArco(atributo)
                            node_imp = self.calcImp(node_filho, x_train, y_train) # Calcula-se a impureza
                            node_filho.setImp(node_imp) # atualiza-se a impureza do nó

                            if node_imp==0:                 # Se a impureza do nó for 0, significa que já não são necessários mais splits
                                node_filho.setFinished(True)

                            
                            node_filho.splitFeitos = raiz.splitFeitos.copy() # O filho tem informação de que atributos foram usados anteriormente para fazer split

                            node_filho.splitFeitos.append(n_coluna) # Indicar que atributos já foram utilizada para fazer um split

                            tree_aux.raiz.filhos.append(node_filho) # Adicionar o nó aos filhos da raiz

                            impTotal += (len(filho)/len(x_train)) * node_imp # Faz-se a média aritmética
                            
                        # Aqui ja temos todos os filhos da arvore
                        dic_imp[len(listaFinal)]=impTotal # Inserir a impureza das folhas deste split
                        listaFinal.append(tree_aux) # Inserir a nova árvore criada na ListaFinal


                indice_menorPureza = min(dic_imp, key=dic_imp.get)
                tree_aux = listaFinal[indice_menorPureza]


                if indice_menorPureza == 0: # Significa que não se vai realizar um split
                    tree_aux.raiz.setFinished(True) # Entao este nó já acabou
                    return tree_aux

                return  tree_aux # Damos return da árvore que tem menos impureza

        return tree # Damos return da arvore dada já com o(s) split(s) feito(s)

    def actYconsoanteDadosNo(self, dados_no, x_train, y_train): # Actualizamos o array y do nó, consoante o array x do nó
        
        novoY=[]
        for exemplo in dados_no:
            novoY.append(y_train[x_train.index(exemplo)])   # Usa-se o .index(exemplo) para saber em que posição do x_train estava o exemplo, e utiliza-se
                                                            # para saber qual a classe desse exemplo, colocando na nova posição do novo array Y

        return novoY

    def poda_um_no(self, node, nodeAPodar): # Faz a poda no nó nodeAPodar
        if node == nodeAPodar: # Verifica se o node é o nodeAPodar comparando as datas
            node.filhos = [] # Se sim remove os filhos, e 
            return node
        
        for filho in node.filhos: # Percorre recursivamente os filhos
            filho = self.poda_um_no(filho, nodeAPodar)
        
        return node
    

    def poda_aux(self, node, lista_podados): # Devolve uma lista com todos os nós que podem fazer poda

        if (node.podeFazerPoda()) and (node not in lista_podados): # Vê se o nó pode fazer poda e se não está na lista 
  
            lista_podados.append(node)  # Adicionamos o nó à lista de nós que podem ser podados
            return lista_podados

        for filho in node.filhos: # Percorre recursivamente os filhos do nó
            lista_podados = self.poda_aux(filho, lista_podados)


        return lista_podados

    
    def poda(self, tree , x_poda, y_poda): # Devolve a arvore podada
        while True:
            list_tree = list() # Guardar as arvore podadas
            dic_poda = {} # Dicionário para associar uma árvore a uma exatidão

            self.tree = copy.deepcopy(tree)
            dic_poda[len(list_tree)]=self.score(x_poda,y_poda)  # Arvore sem nenhuma poda pos:0, para comparar com as arvores podadas
            list_tree.append(self.tree)

            lista_podados = self.poda_aux(self.tree.raiz, list()) # Os nós que podem ser podados
            
            for node in lista_podados: # Percorre-se estes nós 1 a 1

                self.tree = copy.deepcopy(tree) # Coloca-se a ultima árvore calculada, se for a primeira iteração, é a arvore inicial
                    
                self.tree.raiz = self.poda_um_no(self.tree.raiz, node)  # Faz-se a poda desse nó
                dic_poda[len(list_tree)]=self.score(x_poda,y_poda) # adiciona-se a extadião associada a essa árvore
                list_tree.append(self.tree) # adiciona-se a árvore
 

            indice_maiorExatidaoPoda = max(dic_poda.values()) # Qual o maior indice dentro do dicionário de podas
            lista_indice_maiorExatidaoPoda =[k for k,v in dic_poda.items() if v == indice_maiorExatidaoPoda] # quais as arvores que têm esse indice

  
            if len(lista_indice_maiorExatidaoPoda)==1: # Existe só 1 arvore na lista
                tree.raiz = list_tree[lista_indice_maiorExatidaoPoda[0]].raiz

                if lista_indice_maiorExatidaoPoda[0] == 0: # Se esta árvore for a inicial, então significa que não vamos fazer uma poda, e acaba
                    break

            else:   # Se existem mais do que 1 arvore com número máximo de exatidão

                if lista_indice_maiorExatidaoPoda[0] == 0: # Se uma dessas arvores é a inicial
                    tree.raiz=list_tree[lista_indice_maiorExatidaoPoda[1]].raiz # Escolhemos a proxima, de modo a não entrar num loop

                else:
                    tree.raiz=list_tree[lista_indice_maiorExatidaoPoda[0]].raiz # Se não, escolhemos a primeira, visto todas terem a mesma exatidçao

        self.tree.raiz = tree.raiz # Por fim atualizamos a self.tree





    def fit(self,x_train,y_train):

        if self.prune: # Se for para fazer poda, é necessário fazer split dos dados
            x_train, x_poda, y_train, y_poda = train_test_split(x_train, y_train, test_size=0.25, random_state=0)


        self.tree = DTree(Node(x_train,y_train)) # Inicializa-se a arvore

        while not self.tree.isFinished(): # Enquanto existirem split's possiveis
            self.tree = self.split(self.tree)

        self.tree.printTree()
        if self.prune: # Fazer a poda da arvore
            self.poda(self.tree, x_poda, y_poda)
        print("depois da poda")
        self.tree.printTree()

    def whichClass(self, exemplo): # Dado um exemplo devolve a classe a que corresponde
        node = self.tree.raiz
        nosPercorridos=0 # Numero de nós que já percorremos
        nExiste = False

        while not node.isLeaf(): # enquanto nao chegarmos a uma folha, vamos percorer os nós consoante os atributos em exemplo

            for filho in node.filhos: # Percorremos os filhos do nó
                if nosPercorridos<len(filho.splitFeitos): 

                    if filho.arco == exemplo[filho.splitFeitos[nosPercorridos]]: # Se o arco que segue para este nó, for igual ao atributo do exemplo
                    # exemplo[filho.splitFeitos[nosPercorridos]] -> vemos quantos nós foram percorridos, para saber que coluna olhar
                                                                #   depois vemos se o atributo nessa coluna é igual ao arco que liga ao nó
                        node = filho # Dizemos que queremos seguir para esse filho, até encontrarmos uma folha
                        break
                else: # Se o atributo que queremos percorrer, não existe na árvore
                    nExiste=True

            if nExiste:
                break

            nosPercorridos+=1

        return node.classe

    def score(self,x,y):
        
        aux = []
        result = 0

        for exemplo in x:   # Percorrer todos os exemplos e a partir da arvore calcular a classe a que pertence
            aux.append(self.whichClass(exemplo))

        for i in range(0,len(y)): # Percorrer aux e y, comparar os resultados e calcular o scoe
            if aux[i]==y[i]:
                result+=1

        return result/len(y)



class DTree:
    def __init__(self, no):
        self.raiz= no
    
    def isFinished(self): # Dada uma arvore devolve True se todos os nós tiverem finished
        
        if self.raiz.isFinished(): # Se a raiz já acabou
            for filho in self.raiz.filhos: # Percorrer os filhos
                if not DTree(filho).isFinished(): # Se o filho ainda não acabou
                    return False           
        else: # Se a raiz ainda não acabou
            return False

        return True # Se a raiz e todos os seus filhos já acabaram

    def printAuxRec(self,raiz): # Print aux recursivo da arvore

        print(f" {raiz.splitFeitos} vem do arco- {raiz.arco}")
        for filho in raiz.filhos:
            self.printAuxRec(filho)

    def printTree(self):  # Print da arvore
        self.printAuxRec(self.raiz)

    


class Node:
    def __init__(self,data,arrY,imp="2"):
        self.filhos=[] #Cada nó tem filhos
        self.data=data # array x do nó
        self.imp=imp  #impuridade de cada nó
        self.arrY=arrY # as classes presentes neste nó
        self.acabou=False # Se este nó ainda pode fazer split
        self.splitFeitos = [] # O n da coluna de atributos que já foram utilizados para fazer split
        self.classe="Nenhuma" # Classe a que pertence um exemplo se acabar neste nó
        self.arco="nenhum" # Que atributo foi utilizado para chegar, a este nó, a partir do pai

    def setImp(self,imp):
        self.imp=imp

    def setArco(self, arco):
        self.arco=arco

    def isLeaf(self):
        return len(self.filhos)==0

    def setFinished(self, b):
        self.acabou=b

    def isFinished(self):
        return self.acabou or self.filhos!=[]
    
    def podeFazerPoda(self): # Devolve True se todos os filhos de um dado nó são todos folhas, senão False
        if self.isLeaf():
            return False

        for filho in self.filhos:
            if not filho.isLeaf():
                return False
        return True

    def __eq__(self, other): 
        return self.data == other.data
    
    def __str__(self):
        return self.data


# data=np.genfromtxt("vote.csv", delimiter=",", dtype=None, encoding=None)
data=np.genfromtxt("led-train.csv", delimiter=",", dtype=None, encoding=None)
xdata=data[1:,0:-1]    #  dados: da segunda à ultima linha, da primeira à penúltima coluna  
ydata=data[1:,-1]      # classe: da segunda à ultima linha, só última coluna

teste=np.genfromtxt("led-test.csv", delimiter=",", dtype=None, encoding=None)
x_teste=teste[1:,0:-1]    #  dados: da segunda à ultima linha, da primeira à penúltima coluna  
y_teste=teste[1:,-1]      # classe: da segunda à ultima linha, só última coluna

x_train = xdata.tolist() # Necessário para conseguir usar listas e listas de listas do python, em vez de numpy
y_train = ydata.tolist()

x_teste = x_teste.tolist() # Necessário para conseguir usar listas e listas de listas do python, em vez de numpy
y_teste = y_teste.tolist()


# x_train, x_test, y_train, y_test = train_test_split(xdata, ydata, random_state=0)


# Critérios: "entropia" "gini" "erro"
classifier = myDecisionTreeREPrune("entropia",False)
classifier.fit(x_train, y_train)
result = classifier.score(x_teste, y_teste)
print("Percentagem de casos corretamente classificados {:2.2%}".format(result))