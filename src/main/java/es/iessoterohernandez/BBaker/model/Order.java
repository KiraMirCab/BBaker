package es.iessoterohernandez.BBaker.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp paidDate;
    private double total;
    @ManyToOne
    @JoinColumn(nullable = true, name= "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "order_product", 
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
    
    public Order() {}


    public Order(Timestamp creationDate, Timestamp paidDate, double total, User user, List<Product> products) {
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.total = total;
        this.user = user;
        this.products = products;
    }

        
}
