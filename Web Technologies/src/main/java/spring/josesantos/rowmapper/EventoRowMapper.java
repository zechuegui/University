package spring.josesantos.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.josesantos.model.Evento;

public class EventoRowMapper implements RowMapper<Evento> {

	@Override
	public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Evento(rs.getInt("id"), rs.getString("nomeEvento"), rs.getDate("data"), rs.getInt("preco"), rs.getString("descricao"));
	}
}
