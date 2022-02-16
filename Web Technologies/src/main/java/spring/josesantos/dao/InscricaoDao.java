package spring.josesantos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.josesantos.model.Inscricao;
import spring.josesantos.rowmapper.InscricaoRowMapper;
import java.util.List;

@Repository
public class InscricaoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public Inscricao getInscricao(final String email, final int id) {
        return jdbcTemplate.queryForObject(
                "select * from inscricoes where email = ? and id=?",
                new Object[]{email, id},new InscricaoRowMapper());
    }

    public void saveInscricao(final Inscricao i) {
        String sql = "INSERT INTO inscricoes VALUES ('"
                + i.getEmail() + "','"
                + i.getGenero() + "','"
                + i.getEscalao() + "','"
                + i.getId() + "','"
                + i.getPago() + "','"
                + i.getDorsal() + "')";
        jdbcTemplate.execute(sql);
        System.out.println("InscricaoDAO - saved\n" + sql + "\n");
    }

    public List<Inscricao> getInscricoesUtilizador(final String email) {
        String sql = "select * from inscricoes where email='" + email + "';";
        return jdbcTemplate.query(sql,
            new InscricaoRowMapper());
    }


    public List<Inscricao> getTodasInscricoes() {
        return jdbcTemplate.query(
            "select * from inscricoes",
            new InscricaoRowMapper());
    }
}
