package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArtigo{
    
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date(System.currentTimeMillis());
    String data = format.format(date);

    Participante p = new Participante("ze", "123@teste.com", "teste",data);
    Artigo artigo = new Artigo(p,"3","4","5","6");

    @Test
    public void testGetSetMediaAvaliacao(){
        assertEquals(0, artigo.getMediaAvaliacao());

        artigo.setMediaAvaliacao(14.3);
        assertEquals(14.3, artigo.getMediaAvaliacao());
    }

    @Test
    public void testGetSetRevisto(){
        assertEquals(false, artigo.getRevisto());
        artigo.setRevisto();
        assertEquals(true, artigo.getRevisto());

    }

    @Test
    public void testAddAvaliacao(){
        artigo.addAvaliacao("tudo bom", 3);
        assertEquals(0, artigo.getMediaAvaliacao());
        artigo.addAvaliacao("Muito bom", 4);
        assertEquals(0, artigo.getMediaAvaliacao());
        artigo.addAvaliacao("Podia ser melhor", 1);
        assertEquals(2.67, artigo.getMediaAvaliacao());


    }

    @Test
    public void testArtigoEquals(){
        Artigo artigo2 = new Artigo(p,"3","4","5","6");
        Artigo artigo3 = new Artigo(p,"5","4","5","6");
        assertEquals(true, artigo.equals(artigo2));
        assertEquals(false, artigo2.equals(artigo3));
    }

}
