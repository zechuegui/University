package pt.uevora;

import java.util.ArrayList;

public class Artigo{

    Participante autor;
    String nome, instituicao, resumo, texto_artigo;
    
    ArrayList<String> av_escritas;
    ArrayList<Integer> av_quantitativas;
    int nRevisoes;

    double valor_media;
    boolean revisto;
    Conferencia conferencia;
    
    

    public Artigo(Participante autor, String nome, String instituicao, String resumo, String texto_artigo){
        
        this.autor = autor;
        this.nome = nome;
        this.instituicao = instituicao;
        this.resumo = resumo;
        this.texto_artigo = texto_artigo;
        nRevisoes = 0;
        valor_media = 0.0;
        revisto = false;
        av_escritas = new ArrayList<String>();
        av_quantitativas = new ArrayList<Integer>();
        
    }

    public Participante getAutor(){
        return autor;
    }

    public void setMediaAvaliacao(double valor_media){
        this.valor_media = valor_media;
    }

    public double getMediaAvaliacao(){
        return valor_media;
    }

    public void setRevisto(){
        revisto = true;
    }

    public boolean getRevisto(){
        return revisto;
    }

    public void addAvaliacao(String av_escrita, int av_quantitativa){
        av_escritas.add(av_escrita);
        av_quantitativas.add(av_quantitativa); 
        nRevisoes++;

        // É calculado o valor da média, mas não se sabe se está aprovado
        if (nRevisoes>=3){

            revisto=true;
            valor_media=0;

            for (int x : av_quantitativas){
                valor_media += x;
            }

            valor_media /= nRevisoes;
            valor_media = Math.round(valor_media * 100.0) / 100.0;
        }
        
    }

    public String toString(){
        String msg = "Artigo com Nome: "+nome+"\nAutor: "+autor.nome+"\n";
        msg += (revisto)? "Revisto com nota "+valor_media : "Nao revisto";
        return msg;
    }

    public boolean equals(Artigo a){

        if(autor.equals(a.autor) && nome.equals(a.nome)){
            return true;
        }      
 
        return false;
    }


}