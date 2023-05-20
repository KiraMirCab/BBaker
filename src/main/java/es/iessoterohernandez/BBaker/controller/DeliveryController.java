package es.iessoterohernandez.BBaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.DTO.DeliveryDTO;
import es.iessoterohernandez.BBaker.model.Delivery;
import es.iessoterohernandez.BBaker.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    
    @Autowired
    DeliveryService deliveryService;

    @PostMapping("add")
    public Delivery addNew(@RequestBody DeliveryDTO deliveryDto) {
        Delivery delivery = deliveryService.mapToDelivery(deliveryDto);
        System.out.println("Esto es deliveryyyyy" + delivery.getNote() + " " + delivery.getDate());
        deliveryService.addNewDelivery(delivery);
        return delivery;
    }

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Delivery getByID(@PathVariable Long id) {
        return deliveryService.getByID(id);
    }
    @GetMapping("/order/{id}")
    public Delivery findByOrderId(@PathVariable Long id) {
        return deliveryService.findbyOrderId(id);
    }
}
