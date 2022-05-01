package es.iessoterohernandez.BBaker.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iessoterohernandez.BBaker.model.Product;
import es.iessoterohernandez.BBaker.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getByID(@PathVariable Long id) {
        return productService.getByID(id);
    }

    @PostMapping("add")
    public Product addNew(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

}
