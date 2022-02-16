package spring.josesantos.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.josesantos.model.Pagamento;

public class PagamentoRowMapper implements RowMapper<Pagamento> {

	@Override
	public Pagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Pagamento(rs.getString("email"), rs.getInt("id"), rs.getFloat("mb_amount"), rs.getInt("mb_entity"), rs.getInt("mb_reference"));
	}
}