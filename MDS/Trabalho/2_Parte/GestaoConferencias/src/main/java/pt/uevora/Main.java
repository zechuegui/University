package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[]args) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        String data = format.format(date);
    
        Organizador organizador = new Organizador();
        Conferencia conferencia1 = new Conferencia("30/09/2022");

        Participante p1 = new Participante("Jose", "l43017@alunos.uevora.pt", "teste123", data);
        Participante p2 = new Participante("Alberto", "alberto@alunos.uevora.pt", "teste123", data);
        
        Artigo a1 = new Artigo(p1, "Vida dos coelhos", "Universidade de Évora", "Os coelhos têm vida", "teste");
        Artigo a2 = new Artigo(p2, "teste", "Universidade de Évora", "Isto é um teste", "teste");

        Revisor r1 = new Revisor("Carlos", "Carlos@business.com");
        Revisor r2 = new Revisor("Joao", "Joao@business.com");
        Revisor r3 = new Revisor("Gustavo", "Gustavo@business.com");

        organizador.organizarConferencia(conferencia1);
        organizador.setNotaMinima(conferencia1, 3);

        p1.publicarArtigo(conferencia1, a1);
        p2.publicarArtigo(conferencia1, a2);

        organizador.atribuirRevisor(r1, a1);
        organizador.atribuirRevisor(r2, a1);
        organizador.atribuirRevisor(r3, a1);

        organizador.atribuirRevisor(r1, a2);
        organizador.atribuirRevisor(r2, a2);
        organizador.atribuirRevisor(r3, a2);
        

        r1.rever_artigo(a1,"Quase perfeito", 4);
        r2.rever_artigo(a1,"Muito bom", 3);

        p1.detalhesArtigo(a1);

        r3.rever_artigo(a1, "podia ser melhor", 2);

        p1.detalhesArtigo(a1);

        r1.rever_artigo(a2,"Muito mau", 1);
        r2.rever_artigo(a2,"Mais ou menos", 2);
        r3.rever_artigo(a2,"Nunca li algo tao mau", 1);

        p2.detalhesArtigo(a2);

        System.out.println( conferencia1.artigosAExpor());
    }
    
}
