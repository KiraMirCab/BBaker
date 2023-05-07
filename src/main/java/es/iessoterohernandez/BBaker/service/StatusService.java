package es.iessoterohernandez.BBaker.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.DTO.StatusChangeDTO;
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

    public StatusChange addNewStatusChange (Long status_id, OrderO order) {
        OrderStatus status = statusRepository.getById(status_id);
        StatusChange stc = new StatusChange(order, status, new Timestamp(System.currentTimeMillis()));
        return statusChangeRepository.save(stc);
    }

    public StatusChange addNewStatusChange (StatusChangeDTO scDTO) {
        StatusChange stch = new StatusChange();
        stch.setOrder(orderRepository.getById(scDTO.getOrder_id()));
        stch.setStatus(statusRepository.getById(scDTO.getStatus_id()));
        stch.setChangeDate(new Timestamp(System.currentTimeMillis()));
        return statusChangeRepository.save(stch);
    }

    public int deleteByOrderId(Long id) {
        return statusChangeRepository.deleteByOrderId(id);
    }
}
