package spring.josesantos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.josesantos.model.Evento;
import spring.josesantos.rowmapper.EventoRowMapper;
import java.util.List;

@Repository
public class EventoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Evento getEvento(final int id) {
        return jdbcTemplate.queryForObject(
                "select id, nomeEvento, data, preco, descricao from evento where id = ?",
                new Integer[]{id}, new EventoRowMapper());
    }

    public void saveEvento(final Evento e) {
        String sql = "INSERT INTO evento VALUES ('"
                + e.getId() + "','"
                + e.getNomeEvento() + "','"
                + e.getDescricao() + "','"
                + e.getPreco() + "','"
                + e.getData() + "')";
        jdbcTemplate.execute(sql);
        System.out.println("EventoDao - saved\n" + sql + "\n");
    }


    public List<Evento> getEventoList() {
        return jdbcTemplate.query(
            "select * from evento",
            new EventoRowMapper());
    }

    public Integer getIdByName(String nomeEvento){
        return jdbcTemplate.queryForObject("select id from evento where nomeEvento = ?", 
            new String[]{nomeEvento}, Integer.class);
    }

}
