package es.iessoterohernandez.BBaker.DTO;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderODTO {
   
    private Timestamp creationDate;
    private Timestamp paidDate;
    private Double total;
    private Long user_id;
    @Embedded
    private List<OrderProductDTO> orderProductsDTO = new ArrayList<>();   

    public OrderODTO(Timestamp creationDate, Timestamp paidDate, Double total, Long user_id,
            List<OrderProductDTO> orderProductsDTO) {
        this.creationDate = creationDate;
        this.paidDate = paidDate;
        this.total = total;
        this.user_id = user_id;
        this.orderProductsDTO = orderProductsDTO;
    }

    public OrderODTO() {}
}