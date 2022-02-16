package pt.uevora;
import java.util.ArrayList;

public class Participante{

    String nome;
    String email;
    String password;
    boolean isAutor;
    ArrayList<Artigo> lista_artigos;
    String data_atual;

    public Participante(String nome, String email, String password, String data_atual){
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.isAutor = false;
        this.lista_artigos = new ArrayList<>();
        this.data_atual = data_atual;
    }
    
    

    public void setAutor(boolean isAutor){
        this.isAutor = isAutor;
    }

    public boolean isAutor(){
        return isAutor;
    }

    public int getNArtigos(){
        return lista_artigos.size();
    }

    public void publicarArtigo(Conferencia c, Artigo artigo){       
        
        // Se já for autor, verificar se está a submeter uma nova versão do mesmo artigo
        if (isAutor()){
            
            for (Artigo a : lista_artigos){

                // Se for uma nova versão
                if (a.nome.equals(artigo.nome) && this.equals(artigo.getAutor())){
                    
                    // Colocar o novo artigo na posição do antigo
                    lista_artigos.set(lista_artigos.indexOf(a), artigo);
                    c.addArtigo(artigo);
                    return;

                }
            }
        }

        setAutor(true);
        lista_artigos.add(artigo);
        c.addArtigo(artigo);
    }

    public void verEstadoArtigo(Artigo artigo) throws Exception{
        boolean autorDoArtigo = false;
        for (Artigo a : lista_artigos){
            if (a.equals(artigo)){
                autorDoArtigo=true;
                break;
            }
        }

        if(autorDoArtigo){
            System.out.println(artigo);
        }
        else{
            throw new Exception("Não é autor deste artigo");
        }

    }

    public void verDetalhesDaInscricao(){
        System.out.println(this);
    }


    public boolean equals(Participante p){
        return this.nome.equals(p.nome) && this.email.equals(p.email);
    }

    public String toString(){
        String msg = "Nome: " + nome + ", Email: " + email;
        msg += "\n";
        if (isAutor()){
            msg+= "Autor com " + getNArtigos() + " artigos, com os titulos:\n";

            for (Artigo a : lista_artigos){
                msg += a.nome + ";\n";
            }
        }

        return msg;
    }

    //devolve true se data1>data2
    public boolean dataMaiorQue(String data1, String data2){
        String [] arr_data1 = data1.split("/");
        String [] arr_data2 = data2.split("/");

        if (Integer.parseInt(arr_data1[2])>Integer.parseInt(arr_data2[2])){
            return true;
        }
        else if (Integer.parseInt(arr_data1[2])==Integer.parseInt(arr_data2[2])){
            if (Integer.parseInt(arr_data1[1])>Integer.parseInt(arr_data2[1])){
                return true;
            }
            else if (Integer.parseInt(arr_data1[1])==Integer.parseInt(arr_data2[1])){
                if(Integer.parseInt(arr_data1[0])<Integer.parseInt(arr_data2[0])){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public void detalhesArtigo(Artigo artigo) throws Exception{

        for (Artigo a : lista_artigos){
            if (a.equals(artigo)){
                System.out.println(a);
                return;
            }
        }
        throw new Exception("Não é autor deste artigo");


    }

    public void participarNaConferencia(Conferencia c){
        if (dataMaiorQue(c.getDataLimie(), data_atual)){
            c.addParticipante(this);
        }
        
    }
}