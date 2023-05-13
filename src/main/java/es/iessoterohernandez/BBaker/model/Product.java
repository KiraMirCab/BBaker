package es.iessoterohernandez.BBaker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nameENG;
    private String shortDesc;
    private String shortDescENG;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String descriptionENG;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
    private Double price;
    private int discount;
    private boolean specialTransport;
    private boolean active = false;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OrderProducts> orderProducts;
    
    public Product(String name, String nameENG, String shortDesc, String shortDescENG, String description,
            String descriptionENG, String image, Double price, int discount, boolean specialTransport) {
        this.name = name;
        this.nameENG = nameENG;
        this.shortDesc = shortDesc;
        this.shortDescENG = shortDescENG;
        this.description = description;
        this.descriptionENG = descriptionENG;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.specialTransport = specialTransport;
    }

    public Product() {}
     
    
}
