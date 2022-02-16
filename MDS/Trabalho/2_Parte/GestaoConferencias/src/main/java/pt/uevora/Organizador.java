package pt.uevora;

import java.util.ArrayList;

public class Organizador {

    private ArrayList<Conferencia> lista_conferencias;

    public Organizador(){
        lista_conferencias = new ArrayList<>();
    }

    public int getNConferencias(){
        return lista_conferencias.size();
    }

    public void organizarConferencia(Conferencia conferencia){
        lista_conferencias.add(conferencia);
    }

    public void setNotaMinima(Conferencia conferencia, int notaMinima){
        conferencia.setNotaMinima(notaMinima);
    }

    public void atribuirRevisor(Revisor revisor, Artigo artigo){
        revisor.addArtigoARever(artigo);
    }

    public void listarTodasAsInscricoes(Conferencia conferencia){
        ArrayList<Participante> listaParticipantes = conferencia.getListaParticipantes();
        for (Participante p : listaParticipantes){
            System.out.println(p);
        }
    }
    
    public void listarTodosOsArtigos(Conferencia conferencia){
        ArrayList<Artigo> listaArtigos = conferencia.getListaArtigos();
        for (Artigo a : listaArtigos){
            System.out.println(a);
        }
    }
}
