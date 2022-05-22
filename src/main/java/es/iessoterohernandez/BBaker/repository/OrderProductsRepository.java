package es.iessoterohernandez.BBaker.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import es.iessoterohernandez.BBaker.model.OrderProducts;

public interface OrderProductsRepository extends JpaRepository<OrderProducts,Long>{
    
    List<OrderProducts> findByOrderId(Long orderId);
    List<OrderProducts> findByProductId(Long productId);

    @Transactional
    @Modifying
    int deleteByOrderId(Long orderId);
    
}