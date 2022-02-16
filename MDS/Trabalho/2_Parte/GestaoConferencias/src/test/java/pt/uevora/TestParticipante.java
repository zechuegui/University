package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParticipante {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date(System.currentTimeMillis());
    String data = format.format(date);

    Participante p = new Participante("ze", "123@teste.com", "teste", data);
    Artigo artigo = new Artigo(p,"3","4","5","6");
    Conferencia c = new Conferencia("26/8/2023");
    

    @Test
    public void testNewArtigo() throws Exception{
        
        p.setAutor(true);

        p.publicarArtigo(c, artigo);

        assertEquals(1, p.getNArtigos());

        
    }

    @Test
    public void testGetArtigo() throws Exception {
        p.setAutor(true);
        assertEquals(0, p.getNArtigos());
    
        p.publicarArtigo(c, artigo);
        assertEquals(1, p.getNArtigos());


    }

    @Test
    public void testSetAutor(){
        assertEquals(false, p.isAutor());

        p.setAutor(true);
        assertEquals(true, p.isAutor());

    }

    @Test
    public void testNovaVersao(){

        Artigo artigo_novo = new Artigo(p,"3","4","5","99");
        Artigo artigo1 = new Artigo(p,"2","4","5","80");

        assertEquals(0, p.getNArtigos()); 
        p.publicarArtigo(c,artigo);
        assertEquals(1, p.getNArtigos()); 
        p.publicarArtigo(c,artigo1);
        assertEquals(2, p.getNArtigos()); 
        p.publicarArtigo(c,artigo_novo);
        assertEquals(2, p.getNArtigos()); 
    }

    @Test
    public void testDataMenorQue(){
        String data1 = "19/02/2000";
        String data2 = "18/02/2000";

        assertEquals(true, p.dataMaiorQue(data1, data2)); 
    }

    @Test
    public void TestParticipanteEquals(){
        Participante p2 = new Participante("ze", "123@teste.com", "teste", data);
        Participante p3 = new Participante("ze", "423@teste.com", "teste", data);

        assertEquals(true, p.equals(p2));
        assertEquals(false, p2.equals(p3));
    }

}
