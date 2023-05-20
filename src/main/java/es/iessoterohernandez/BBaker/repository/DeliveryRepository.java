package es.iessoterohernandez.BBaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iessoterohernandez.BBaker.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer>{
    Delivery getById(Long id);
    Delivery findByOrderId(Long orderId);
}
