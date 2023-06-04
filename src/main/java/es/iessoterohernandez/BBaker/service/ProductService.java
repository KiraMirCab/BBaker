package es.iessoterohernandez.BBaker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.BBaker.model.Product;
import es.iessoterohernandez.BBaker.repository.ProductRepository;

@Service
public class ProductService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        LOGGER.info("Obteniendo todos los productos");
        return productRepository.findAll();
    }

    public Product addNewProduct(Product product){
        LOGGER.info("Añadiendo un nuevo producto: {}", product);
        return productRepository.save(product);
    }

    public Product getByID(Long id){
        LOGGER.info("Obteniendo producto por ID: {}", id);
        return productRepository.getById(id);
    }
}