package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConferencia {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date(System.currentTimeMillis());
    String data = format.format(date);
    Participante p = new Participante("ze", "123@teste.com", "teste",data);
    Artigo artigo = new Artigo(p,"3","4","5","6");
    Conferencia c = new Conferencia("20/07/2021");

    @Test
    public void testDataValida() throws Exception{
        assertEquals(false, c.dataValida("0/5/2020"));
        assertEquals(false, c.dataValida("1/13/2020"));
        assertEquals(false, c.dataValida("1/12/2051"));
        assertEquals(true, c.dataValida("1/1/2050"));
        assertEquals(true, c.dataValida("01/01/2021"));
        assertEquals(true, c.dataValida("1/12/2050"));
    }

    @Test
    public void testSetGetDataLimite() throws Exception{
        c.setDataLimite("30/07/2021");
        assertEquals("30/07/2021", c.getDataLimie());
    }

    @Test
    public void testAddGetArtigo(){
        assertEquals(0, c.getNArtigos());

        c.addArtigo(artigo);

        assertEquals(1, c.getNArtigos());
    }

    @Test
    public void testAddGetParticipante(){
        assertEquals(0, c.getNParticipantes());

        c.addParticipante(p);

        assertEquals(1, c.getNParticipantes());
    }

}
