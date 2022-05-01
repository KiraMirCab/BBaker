package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.Order;
import es.iessoterohernandez.BBaker.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    public Order addNewOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getByID(Long id){
        return orderRepository.getById(id);
    }
}
