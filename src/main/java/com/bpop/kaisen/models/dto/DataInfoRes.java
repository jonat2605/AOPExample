package com.bpop.kaisen.models.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataInfoRes {

    private Integer statusCode;
    private String responseStatus;
    private List<Object> dataInfo;

}
