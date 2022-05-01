package es.iessoterohernandez.BBaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.Order;
import es.iessoterohernandez.BBaker.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping("add")
    public Order addNew(@RequestBody Order order) {
        return orderService.addNewOrder(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Order getByID(@PathVariable Long id) {
        return orderService.getByID(id);
    }
}
