package es.iessoterohernandez.BBaker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
