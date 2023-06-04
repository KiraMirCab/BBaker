package es.iessoterohernandez.BBaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.iessoterohernandez.BBaker.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,Integer>{
    Delivery getById(Long id);
    Delivery findByOrderId(Long orderId);

    @Query("SELECT d FROM Delivery d " +
            "JOIN d.order o " +
            "JOIN o.orderStatus os " +
            "WHERE os.name = 'Listo para entrega'")
        List<Delivery> findAllReadyForDelivery();
}
