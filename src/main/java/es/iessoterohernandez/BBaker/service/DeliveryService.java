package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.DTO.DeliveryDTO;
import es.iessoterohernandez.BBaker.model.Delivery;
import es.iessoterohernandez.BBaker.model.OrderO;
import es.iessoterohernandez.BBaker.repository.DeliveryRepository;

@Service
public class DeliveryService {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(DeliveryService.class);

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
        LOGGER.info("Añadiendo una nueva entrega");
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllOrders() {
        LOGGER.info("Obteniendo todas las entregas");
        return deliveryRepository.findAll();
    }

    public Delivery getByID(Long id){
        LOGGER.info("Obteniendo entrega por ID: " + id);
        return deliveryRepository.getById(id);
    }

    public Delivery findbyOrderId(Long id) {
        LOGGER.info("Obteniendo entrega por ID de pedido: " + id);
        return deliveryRepository.findByOrderId(id);
    }

    public List<Delivery> allReadyForDelivery() {
        LOGGER.info("Obteniendo las entregas activas (Pedidos listos para la entrega)");
        return deliveryRepository.findAllReadyForDelivery();
    }
}