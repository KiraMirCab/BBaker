package es.iessoterohernandez.BBaker.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.OrderO;
import es.iessoterohernandez.BBaker.model.OrderStatus;
import es.iessoterohernandez.BBaker.model.StatusChange;
import es.iessoterohernandez.BBaker.repository.OrderRepository;
import es.iessoterohernandez.BBaker.repository.OrderStatusRepository;
import es.iessoterohernandez.BBaker.repository.StatusChangeRepository;

@Service
public class StatusService {
    
    @Autowired
    OrderStatusRepository statusRepository;

    @Autowired
    StatusChangeRepository statusChangeRepository;

    @Autowired
    OrderRepository orderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    public OrderStatus getStatus(Long id) {
        LOGGER.info("Obteniendo estado con ID: {}", id);
        return statusRepository.getById(id);
    }

    public List<OrderStatus> getAllStatuses() {
        LOGGER.info("Obteniendo todos los estados");
        return statusRepository.findAll();
    }

    /* Este método recoge el id del estado y el perdido,
     * extrae el estado de la BD y se lo asigna al pedido 
     * antes de actualizarlo en la BD (con el método 'save').
     * Además añade una nueva entrada en el historial de cambios 
     * con el estado nuevo y la hora en el que se produjo el cambio
     */
    public OrderO setOrderStatus(Long status_id, Long order_id) {
        LOGGER.info("Estableciendo estado del pedido. ID de estado: {}, ID de pedido: {}", status_id, order_id);
        OrderStatus status = statusRepository.getById(status_id);
        OrderO order = orderRepository.getById(order_id);
        StatusChange stc = new StatusChange(order, status, new Timestamp(System.currentTimeMillis()));
        statusChangeRepository.save(stc);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    public int deleteByOrderId(Long id) {
        LOGGER.info("Eliminando cambios de estado por ID de pedido: {}", id);
        return statusChangeRepository.deleteByOrderId(id);
    }

    public List<StatusChange> getStatusChangesByOrderId(Long orderId) {
        LOGGER.info("Obteniendo cambios de estado por ID de pedido: {}", orderId);
        return statusChangeRepository.findByOrderId(orderId);
    }
}