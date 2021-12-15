import numpy as np
from sklearn.tree import DecisionTreeClassifier
from sklearn import preprocessing

train = np.genfromtxt("train.csv", delimiter=",", dtype=None, encoding=None)
x_train = train[1:,0:-1]     
y_train = train[1:,-1]


test = np.genfromtxt("test.csv", delimiter=",", dtype=None, encoding=None)
idEstudante = test [1:,0]
f_test = test[1:,0:]


teste = preprocessing.LabelEncoder() # É necessário fazer um labelEnconder na coluna Program
x_train[:,1] = teste.fit_transform(x_train[:,1])
f_test[:,1] = teste.fit_transform(f_test[:,1])


DTree = DecisionTreeClassifier(random_state=0)

DTree.fit(x_train,y_train)

y_pred = DTree.predict(f_test) # Fazer o predict co

final = np.stack([idEstudante,y_pred], axis=1)

final = np.insert(final,0,["Id","Failure"],axis=0) # Adicionar o cabeçalho à matrix final

np.savetxt("submission.csv", final,delimiter=',' ,fmt='%s') # Colocar a matriz no ficheiro .csv para a submissão