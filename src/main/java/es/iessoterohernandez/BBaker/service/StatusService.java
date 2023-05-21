package es.iessoterohernandez.BBaker.service;

import java.sql.Timestamp;
import java.util.List;

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

    public OrderStatus getStatus(Long id) {
        return statusRepository.getById(id);
    }

    public List <OrderStatus> getAllStatuses() {
        return statusRepository.findAll();
    }

    /* Este método recoge el id del estado y el perdido,
     * extrae el estado de la BD y se lo asigna al pedido 
     * antes de actualizarlo en la BD (con el método 'save').
     * Además añade una nueva entrada en el historial de cambios 
     * con el estado nuevo y la hora en el que se produjo el cambio
     */
    public OrderO setOrderStatus (Long status_id, Long order_id) {
        OrderStatus status = statusRepository.getById(status_id);
        OrderO order = orderRepository.getById(order_id);
        StatusChange stc = new StatusChange(order, status, new Timestamp(System.currentTimeMillis()));
        statusChangeRepository.save(stc);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    public int deleteByOrderId(Long id) {
        return statusChangeRepository.deleteByOrderId(id);
    }
}
