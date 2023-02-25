package sg.edu.nus.iss.day24_redo2.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day24_redo2.models.OrderDetails;
import sg.edu.nus.iss.day24_redo2.models.Orders;

@Controller
public class OrderController {

    @PostMapping(path = "/order")
    public String getOrders(@RequestBody MultiValueMap<String, String> form, 
        Model model, HttpSession session){
        
        List<OrderDetails> orderItems = (List<OrderDetails>) session
            .getAttribute("order");
        if(null == orderItems){
            orderItems = new LinkedList<>();
            session.setAttribute("order", orderItems);
        }
        System.out.println(">>>>>>>>>" + orderItems);
        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));

        System.out.println(">>>>>>>>>" + item + ">>>>>>>>" + quantity);
        orderItems.add(new OrderDetails(item, quantity));
        Orders ord = new Orders();
        ord.setOrderDetails(orderItems);
        ord.setCustomerName(form.getFirst("name"));
        session.setAttribute("checkout", ord);
        model.addAttribute("order", orderItems);
        return "order";
    }
    
}
