package pt.uevora;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrganizador {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date(System.currentTimeMillis());
    String data = format.format(date);

    Participante p = new Participante("ze", "123@teste.com", "teste",data);
    Conferencia c = new Conferencia("25/9/2023");
    Artigo artigo = new Artigo(p,"3","4","5","6");
    Revisor revisor1 = new Revisor("ze","123@teste.com");
    Revisor revisor2 = new Revisor("joao","joao@teste.com");
    Organizador o = new Organizador();

    @Test
    public void testOrganizarConferencia(){
        assertEquals(0, o.getNConferencias());

        o.organizarConferencia(c);

        assertEquals(1, o.getNConferencias());
        
    }

    @Test
    public void testSetNotaMinima(){
        Conferencia c = new Conferencia("25/7/2021");

        assertEquals(-1, c.getNotaMinima());

        o.setNotaMinima(c, 3);
        assertEquals(3, c.getNotaMinima());
        
    }

    @Test
    public void testAtribuirRevisor(){

        assertEquals(0, revisor1.artigos_a_rever.size());
        o.atribuirRevisor(revisor1, artigo);
        assertEquals(1, revisor1.artigos_a_rever.size());
    }
}
