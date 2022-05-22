package es.iessoterohernandez.BBaker.repository;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import es.iessoterohernandez.BBaker.model.OrderO;

public interface OrderRepository extends JpaRepository<OrderO,Integer>{
    OrderO getById(Long id);
    
    @Transactional
    @Modifying
    int deleteById(Long id);
    
    @Transactional
    @Modifying
    @Query(value = "update ordero o set o.paid_date = ? where o.id = ?", 
      nativeQuery = true)
    int setPaidDate(Timestamp paidDate, Long id);
}
