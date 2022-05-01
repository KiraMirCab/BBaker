package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.Product;
import es.iessoterohernandez.BBaker.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public Product getByID(Long id){
        return productRepository.getById(id);
    }
}
