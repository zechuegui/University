package spring.josesantos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.josesantos.model.Tempo;
import spring.josesantos.rowmapper.TempoRowMapper;
import java.util.List;

@Repository
public class TempoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveTempo(final Tempo t) {
        String sql = "INSERT INTO tempos VALUES ('"
                + t.getDorsal() + "','"
                + t.getId() + "','"
                + t.getPonto() + "','"
                + t.getTempo() + "');";
        jdbcTemplate.execute(sql);
        System.out.println("TempoDao - saved\n" + sql + "\n");
    }

    public List<Tempo> getTemposById(final int id){
        return jdbcTemplate.query(
            "select * from tempos where id=?", new Integer[]{id} ,new TempoRowMapper());
    }

    public List<Tempo> getTodosTempos(){
        return jdbcTemplate.query(
            "select * from tempos", new TempoRowMapper());
    }
}
