package pt.uevora;

import java.util.ArrayList;

public class Revisor {
    
    boolean isAutor;
    String nome;
    String email;
    ArrayList<Artigo> artigos_a_rever;


    public Revisor(String nome, String email){
        this.nome = nome;
        this.email = email;
        isAutor = false;
        artigos_a_rever = new ArrayList<>();
    }

    public void setIsAutor(boolean isAutor){
        this.isAutor = isAutor;
    }

    public boolean isAutor(){
        return isAutor;
    }

    public boolean rever_artigo(Artigo artigo ,String av_escrita, int av_quantitativa) throws Exception{
        // É preciso ver se o revisor não é autor deste artigo
        boolean pode_rever = false;
        for (Artigo a : artigos_a_rever){
            if (a==artigo){
                pode_rever = true;
            }
        }

        if(!pode_rever){
            throw new Exception("Artigo não foi atribuido a este revisor");
        }

        if (!(artigo.getAutor().nome.equals(this.nome) && artigo.getAutor().email.equals(this.email))){
            artigo.addAvaliacao(av_escrita, av_quantitativa);
            return true;
        }
        else{
            throw new Exception("Revisor é autor deste artigo");
        }
    }

    public void addArtigoARever(Artigo artigo){
        artigos_a_rever.add(artigo);
    }
}
