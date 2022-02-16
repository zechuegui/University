package pt.uevora;
import java.util.ArrayList;

public class Conferencia {
    
    String dataLimite;
    ArrayList<Artigo> lista_artigos;
    ArrayList<Participante> lista_participantes;
    double notaMinima;

    public Conferencia(String dataLimite){
        
        if (dataValida(dataLimite)){
            this.dataLimite=dataLimite;
        }

        notaMinima = -1;
        lista_artigos = new ArrayList<>();
        lista_participantes = new ArrayList<>();
    }

    public boolean dataValida(String dataLimite){
 
        String [] verificar = dataLimite.split("/");
        
        if (verificar.length==3){
            boolean primeiro_char = (0<Integer.parseInt(verificar[0])) && (Integer.parseInt(verificar[0])<32);
            boolean segundo_char = (0<Integer.parseInt(verificar[1])) && (Integer.parseInt(verificar[1])<13);
            boolean terceiro_char = (2020<Integer.parseInt(verificar[2])) && (Integer.parseInt(verificar[2])<2051);

            if (primeiro_char && segundo_char && terceiro_char){
                return true;
            }
        }

        return false;
    }

    public void setDataLimite(String dataLimite){
        
        if (dataValida(dataLimite)){
            this.dataLimite = dataLimite;
        }
    }

    public String getDataLimie(){
        return dataLimite;
    }

    public int getNArtigos(){
        return lista_artigos.size();
    }

    public int getNParticipantes(){
        return lista_participantes.size();
    }

    public void addParticipante(Participante p){
        lista_participantes.add(p);
    }

    public ArrayList<Participante> getListaParticipantes(){
        return lista_participantes;
    }

    public void addArtigo(Artigo artigo){

        lista_artigos.add(artigo);
    }

    public void setNotaMinima(int notaMinima){
        this.notaMinima = notaMinima;
    }

    public ArrayList<Artigo> getListaArtigos(){
        return lista_artigos;
    }

    public double getNotaMinima(){
        return notaMinima;
    }

    public ArrayList<Artigo> artigosAExpor(){

        ArrayList<Artigo> aux = new ArrayList<>();

        for (Artigo a : lista_artigos){
            if(a.getMediaAvaliacao()>=notaMinima){
                aux.add(a);
            }
        }

        return aux;
    }
}
