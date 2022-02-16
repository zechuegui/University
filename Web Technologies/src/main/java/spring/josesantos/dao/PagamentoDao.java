package spring.josesantos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.josesantos.model.Pagamento;
import spring.josesantos.rowmapper.PagamentoRowMapper;
import java.util.List;

@Repository
public class PagamentoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePagamento(final Pagamento p) {
		String sql = "Insert into pagamento values ('" +
			p.getEmail() + "','" +
			p.getId() + "','" +
			p.getMbAmount() + "','" +
			p.getMbEntity() + "','" +
			p.getMbReference() + "');";

		jdbcTemplate.execute(sql);
        System.out.println("PagamentoDao - saved\n" + sql + "\n");
    }

    public List<Pagamento> getTodosPagamentos(){
            return jdbcTemplate.query(
                "select * from pagamento", new PagamentoRowMapper());
    }

}
