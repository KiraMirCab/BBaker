package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.DTO.DeliveryDTO;
import es.iessoterohernandez.BBaker.model.Delivery;
import es.iessoterohernandez.BBaker.model.OrderO;
import es.iessoterohernandez.BBaker.repository.DeliveryRepository;

@Service
public class DeliveryService {
    
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderService orderService;

    public Delivery mapToDelivery(DeliveryDTO deliveryDto) {
        OrderO corrOrder = orderService.getByID(deliveryDto.getOrder_id());
        Delivery delivery = new Delivery(deliveryDto.getDate(), deliveryDto.getAddress(), deliveryDto.getRecipientName(), 
        deliveryDto.getRecipientPhone(), deliveryDto.getNote(), deliveryDto.isSurprise(), deliveryDto.isSpecialTransport(),
        corrOrder);
        return delivery;
    }

    public Delivery addNewDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllOrders() {
        return deliveryRepository.findAll();
    }

    public Delivery getByID(Long id){
        return deliveryRepository.getById(id);
    }

    public Delivery findbyOrderId(Long id) {
        return deliveryRepository.findByOrderId(id);
    }
}
