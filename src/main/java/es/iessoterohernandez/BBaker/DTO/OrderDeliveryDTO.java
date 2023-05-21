package es.iessoterohernandez.BBaker.DTO;

import java.util.Date;

import javax.persistence.Embedded;

import es.iessoterohernandez.BBaker.model.OrderO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDeliveryDTO {
    @Embedded
    private OrderO order;
    private Date date;
    private String address;
    private String note;
    private boolean surprise;
    private boolean specialTransport;
    
    
    public OrderDeliveryDTO(OrderO order, Date date, String address, String note, boolean surprise, boolean specialTransport) {
        this.order = order;
        this.date = date;
        this.address = address;
        this.note = note;
        this.surprise = surprise;
        this.specialTransport = specialTransport;
    }

    public OrderDeliveryDTO() {}
}
