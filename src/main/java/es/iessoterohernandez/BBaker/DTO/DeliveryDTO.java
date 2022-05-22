package es.iessoterohernandez.BBaker.DTO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {
    private Date date;
    private String address;
    private String recipientName;
    private String recipientPhone;
    private String note;
    private boolean surprise;
    private boolean specialTransport;
    private Long order_id;
    
    public DeliveryDTO(Date date, String address, String recipientName, String recipientPhone, String note,
            boolean surprise, boolean specialTransport, Long order_id) {
        this.date = date;
        this.address = address;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.note = note;
        this.surprise = surprise;
        this.specialTransport = specialTransport;
        this.order_id = order_id;
    }

    public DeliveryDTO() {
    }

}
