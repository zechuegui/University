package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRevisor {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date(System.currentTimeMillis());
    String data = format.format(date);

    Participante p = new Participante("ze", "123@teste.com", "teste",data);
    Artigo artigo = new Artigo(p,"3","4","5","6");
    Revisor revisor1 = new Revisor("ze","123@teste.com");
    Revisor revisor2 = new Revisor("joao","joao@teste.com");
    Organizador o = new Organizador();

    @Test
    public void testIsSetAutor(){
        assertEquals(false, revisor1.isAutor());
        revisor1.setIsAutor(true);
        assertEquals(true, revisor1.isAutor());
        assertEquals(false, revisor2.isAutor());
    }

    @Test
    public void testeReverArtigo() throws Exception{
        assertThrows(Exception.class, () -> revisor1.rever_artigo(artigo, "123", 1));

        assertThrows(Exception.class, () -> revisor2.rever_artigo(artigo, "123", 1));

        o.atribuirRevisor(revisor2, artigo);

        assertEquals(true, revisor2.rever_artigo(artigo, "123", 1));

    }

    @Test
    public void testAddArtigoARever(){
        assertEquals(0, revisor1.artigos_a_rever.size());
        revisor1.addArtigoARever(artigo);
        assertEquals(1, revisor1.artigos_a_rever.size());
    }

}
