package es.iessoterohernandez.BBaker.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.BBaker.model.StatusChange;

@Repository
public interface StatusChangeRepository extends JpaRepository<StatusChange,Integer>{

    List<StatusChange> findByOrderId(Long orderId);
   // List<StatusChange> findByStatusId(Long Id);

    @Transactional
    @Modifying
    int deleteByOrderId(Long orderId);
}
