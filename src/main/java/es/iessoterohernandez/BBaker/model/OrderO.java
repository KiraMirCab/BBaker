package es.iessoterohernandez.BBaker.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderProducts> orderProducts;
    @ManyToOne
    @JoinColumn
    private OrderStatus orderStatus;
    

    public OrderO() {}

    public OrderO(Long id, Timestamp creationDate, Timestamp paidDate, Double total, User user,
            List<OrderProducts> orderProducts, OrderStatus orderStatus) {
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.total = total;
        this.user = user;
        this.orderProducts = orderProducts;
        this.orderStatus = orderStatus;
    }    

    
}
