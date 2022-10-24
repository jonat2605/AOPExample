package com.bpop.kaisen.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "audit_info")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "INSERT_DATE")
    private Date date;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

}
