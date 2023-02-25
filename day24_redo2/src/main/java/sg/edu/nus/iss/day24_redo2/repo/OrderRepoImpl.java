package sg.edu.nus.iss.day24_redo2.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24_redo2.models.Orders;

import static sg.edu.nus.iss.day24_redo2.repo.Queries.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

@Repository
public class OrderRepoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer insertOrders(Orders order){

        // KeyHolder is an interface in Spring Framework's jdbc.core package 
        // that provides a way to retrieve generated keys from an insert 
        // operation in a database.
        // GeneratedKeyHolder is a concrete implementation of the KeyHolder interface 
        // that allows you to retrieve the generated keys from an insert operation.
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        java.util.Date utilDate = timestamp;
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);

        // Statement.RETURN_GENERATED_KEYS is a constant in the java.sql.Statement interface. 
        // It is used to specify that the generated keys should be made available for retrieval
        // after executing an SQL INSERT statement. 
        // When this flag is set, the driver will return the auto-generated keys as a ResultSet
        // object that can be processed to retrieve the generated key values.
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                SQL_INSERTORDERS, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, sqlDate);
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getShipAddress());
            ps.setString(4, order.getNotes());
            ps.setFloat(5, 0);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
