package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.Delivery;
import es.iessoterohernandez.BBaker.repository.DeliveryRepository;

@Service
public class DeliveryService {
    
    @Autowired
    DeliveryRepository deliveryRepository;

    public Delivery addNewDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllOrders() {
        return deliveryRepository.findAll();
    }

    public Delivery getByID(Long id){
        return deliveryRepository.getById(id);
    }
}
