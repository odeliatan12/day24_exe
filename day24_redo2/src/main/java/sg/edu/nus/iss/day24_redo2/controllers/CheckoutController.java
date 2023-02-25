package sg.edu.nus.iss.day24_redo2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day24_redo2.exception.OrderException;
import sg.edu.nus.iss.day24_redo2.models.Orders;
import sg.edu.nus.iss.day24_redo2.service.OrderService;

@Controller
public class CheckoutController {

    @Autowired
    private OrderService service;

    @PostMapping(path = "/checkout")
    public String insertOrders(Model model, HttpSession session) 
        throws OrderException{
        
        Orders order = (Orders) session.getAttribute("checkout");
        service.createPurchaseOrder(order);
        session.invalidate();
        return "checkout";
    }
    
}
