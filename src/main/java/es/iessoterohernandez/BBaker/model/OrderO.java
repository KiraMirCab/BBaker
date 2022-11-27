package es.iessoterohernandez.BBaker.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp creationDate;
    private Timestamp paidDate;
    private Double total;
    @ManyToOne
    @JoinColumn(nullable = true)
    private User user;
    @OneToMany(mappedBy = "product")
    private List<OrderProducts> orderProducts = new ArrayList<>();
    @OneToOne(mappedBy = "order")
    private Delivery delivery;
    
    public OrderO() {}

    public OrderO(Long id, Timestamp creationDate, Timestamp paidDate, Double total, User user,
            List<OrderProducts> orderProducts) {
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.total = total;
        this.user = user;
        this.orderProducts = orderProducts;
    }    

    
}
