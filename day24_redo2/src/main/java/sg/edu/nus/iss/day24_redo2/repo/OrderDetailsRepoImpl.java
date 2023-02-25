package sg.edu.nus.iss.day24_redo2.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24_redo2.models.OrderDetails;

import static sg.edu.nus.iss.day24_redo2.repo.Queries.*;

@Repository
public class OrderDetailsRepoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertBulkOrderDetails(List<OrderDetails> orderDetails, Integer orderId){
        
        List<Object[]> params =  orderDetails.stream()
            .map(orders -> new Object[]{
                orderId, orders.getProduct(), orders.getUnitPrice(), 
                0, orders.getQuantity()})
            .collect(Collectors.toList());
        
        jdbcTemplate.batchUpdate(SQL_INSERTORDERDETAILS, params);
    }
    
}
