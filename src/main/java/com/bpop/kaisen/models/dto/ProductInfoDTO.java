package com.bpop.kaisen.models.dto;

import com.bpop.kaisen.models.entities.Product;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDTO {

    private Long userId;
    private String psw;
    private Product product;

}
