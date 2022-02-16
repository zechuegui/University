package spring.josesantos.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.josesantos.model.Utilizador;

public class UserRowMapper implements RowMapper<Utilizador> {

	@Override
	public Utilizador mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Utilizador(rs.getString("nomeUtilizador"), rs.getString("password"), rs.getString("email"), rs.getString("role"));
	}

}
