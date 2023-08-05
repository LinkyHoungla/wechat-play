package com.example.server.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Balance {
    private Integer id;
    private String uid;
    private String sid;
    private BigDecimal balance;
    private String content;
    private Date createTime;
    private Date updateTime;
}
