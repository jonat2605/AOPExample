package com.bpop.kaisen.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity(name = "audit_info")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "INSERT_DATE")
    private Calendar date;

    @Column(name = "USER_NAME_INSERT")
    private String userName;

    @Column(name = "PRODUCT_NAME")
    private String productName;

}
