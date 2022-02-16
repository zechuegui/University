package spring.josesantos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.josesantos.model.Utilizador;
import spring.josesantos.rowmapper.UserRowMapper;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Utilizador getUser(final String email) {
        return jdbcTemplate.queryForObject(
                "select nomeUtilizador, password, email, role from utilizadores where email = ?",
                new String[]{email}, new UserRowMapper());
    }

    public void saveUser(final Utilizador u) {
        String sql = "INSERT INTO utilizadores VALUES ('"
                + u.getUsername() + "','"
                + u.getPassword() + "','"
                + u.getEmail() + "','"
                + u.getRole() + "', 1)";   // 1 == enabled
        jdbcTemplate.execute(sql);
        System.out.println("UserDao - saved\n" + sql + "\n");
    }

    public List<String> getUsernameList() {
        return jdbcTemplate.queryForList("select nomeUtilizador FROM utilizadores", String.class);
    }

}
