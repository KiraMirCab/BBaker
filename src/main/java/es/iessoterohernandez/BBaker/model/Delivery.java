package es.iessoterohernandez.BBaker.model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Date date;
    private String address;
    private String recipientName;
    private String recipientPhone;
    private String note;
    private boolean surprise;
    private boolean specialTransport;
    @OneToOne
    @JoinColumn
    private OrderO order;
    
    public Delivery(Date date, String address, String recipientName, String recipientPhone, String note,
            boolean surprise, boolean specialTransport, OrderO order) {
        this.date = date;
        this.address = address;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.note = note;
        this.surprise = surprise;
        this.specialTransport = specialTransport;
        this.order = order;
    }

    public Delivery() {}
}
