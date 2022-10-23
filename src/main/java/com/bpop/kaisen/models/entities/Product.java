package com.bpop.kaisen.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product implements Serializable {

    @Id
    private Integer id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PRODUCT_QUANTITY")
    private Integer quantity;

}
