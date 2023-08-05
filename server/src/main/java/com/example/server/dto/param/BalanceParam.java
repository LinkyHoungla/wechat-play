package com.example.server.dto.param;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceParam {
    private Integer id;
    private String uid;
    private String sid;
    private BigDecimal balance;
    private String content;
}
