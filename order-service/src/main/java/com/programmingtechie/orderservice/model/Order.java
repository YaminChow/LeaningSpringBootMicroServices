package com.programmingtechie.orderservice.model;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemList;


}
