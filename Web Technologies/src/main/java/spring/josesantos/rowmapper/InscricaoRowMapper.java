package spring.josesantos.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.josesantos.model.Inscricao;

public class InscricaoRowMapper implements RowMapper<Inscricao> {

	@Override
	public Inscricao mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Inscricao(rs.getString("email"),rs.getString("genero"), rs.getString("escalao"),rs.getInt("id"), rs.getInt("pago"), rs.getInt("dorsal"));
	}

}