package sg.edu.nus.iss.day24_redo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24_redo2.exception.OrderException;
import sg.edu.nus.iss.day24_redo2.models.Orders;
import sg.edu.nus.iss.day24_redo2.repo.OrderDetailsRepoImpl;
import sg.edu.nus.iss.day24_redo2.repo.OrderRepoImpl;

@Service
public class OrderService {

    @Autowired
    private OrderRepoImpl orderRepoImpl;

    @Autowired
    private OrderDetailsRepoImpl orderDetailsRepoImpl;

    @Transactional(rollbackFor = OrderException.class)
    public void createPurchaseOrder(Orders order) throws OrderException{

        Integer gameId = orderRepoImpl.insertOrders(order);

        if(order.getOrderDetails().size() >3){
            throw new OrderException();
        }
        orderDetailsRepoImpl.insertBulkOrderDetails(order.getOrderDetails(), gameId);
    }
    
}
