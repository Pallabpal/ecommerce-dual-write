package com.myproject.ecommerce.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String customerId;
    private List<String> items;
}
