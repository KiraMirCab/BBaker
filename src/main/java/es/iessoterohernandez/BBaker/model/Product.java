package es.iessoterohernandez.BBaker.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Blob imageBlob;
    private Double price;
    private int discount;
    private boolean specialTransport;
    private boolean active = false;
    
    public Product(String name, String description, Blob imageBlob, Double price, int discount,
            boolean specialTransport) {
        this.name = name;
        this.description = description;
        this.imageBlob = imageBlob;
        this.price = price;
        this.discount = discount;
        this.specialTransport = specialTransport;
    }
     
}
