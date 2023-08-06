package com.example.server.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {
    private Integer id;
    private String uid;
    private BigDecimal balance;
    private String content;
    private String createTime;
    private String updateTime;
}
