package es.iessoterohernandez.BBaker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String nameEng;
    private boolean active;

    public OrderStatus(String name, String engName, boolean active) {
        this.name = name;
        this.active = active;
    }

    public OrderStatus() {}
}