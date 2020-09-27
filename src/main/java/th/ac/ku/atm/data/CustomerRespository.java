package th.ac.ku.atm.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.ac.ku.atm.Model.Customer;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface CustomerRespository extends JpaRepository<Customer,Integer> {



}
