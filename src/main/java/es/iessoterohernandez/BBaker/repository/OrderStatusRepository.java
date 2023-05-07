package es.iessoterohernandez.BBaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.BBaker.model.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer>{

    OrderStatus getById(Long id);
}
