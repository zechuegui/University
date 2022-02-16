package spring.josesantos.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.josesantos.model.Tempo;

public class TempoRowMapper implements RowMapper<Tempo> {

	@Override
	public Tempo mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Tempo(rs.getInt("dorsal"), rs.getInt("id"), rs.getString("ponto"), rs.getString("tempo"));
	}    
}
