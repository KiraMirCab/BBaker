package es.iessoterohernandez.BBaker.model;

import java.sql.Timestamp;

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
public class StatusChange {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @ManyToOne
    @JoinColumn
    private OrderO order;

    @ManyToOne
    @JoinColumn
    private OrderStatus status;

    private Timestamp changeDate;

    public StatusChange(OrderO order, OrderStatus status, Timestamp changeDate) {
        this.order = order;
        this.status = status;
        this.changeDate = changeDate;
    }

    public StatusChange() {}
}
