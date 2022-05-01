package es.iessoterohernandez.BBaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iessoterohernandez.BBaker.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    
    List<Product> findByNameContainingIgnoreCase(String name);
    Product getById(Long id);
    
}
