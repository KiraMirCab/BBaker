package es.iessoterohernandez.BBaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.Delivery;
import es.iessoterohernandez.BBaker.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    
    @Autowired
    DeliveryService deliveryService;

    @PostMapping("add")
    public Delivery addNew(@RequestBody Delivery delivery) {
        return deliveryService.addNewDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Delivery getByID(@PathVariable Long id) {
        return deliveryService.getByID(id);
    }
}
