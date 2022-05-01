package es.iessoterohernandez.BBaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iessoterohernandez.BBaker.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
    Order getById(Long id);
}
