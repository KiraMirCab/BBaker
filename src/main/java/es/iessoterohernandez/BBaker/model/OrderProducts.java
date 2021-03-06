package es.iessoterohernandez.BBaker.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Embeddable
public class OrderProducts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    
    @ManyToOne
    @JoinColumn
    private OrderO order;

    @ManyToOne
    @JoinColumn
    private Product product;

    private double price;
    private int quantity;
    
    public OrderProducts(OrderO order, Product product, double price, int quantity) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderProducts() {}    
}
