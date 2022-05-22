package es.iessoterohernandez.BBaker.DTO;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderProductDTO {
    private Long product_id;
    private double price;
    private int quantity;
    
    public OrderProductDTO(Long product_id, double price, int quantity) {
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderProductDTO() {}
   
}
