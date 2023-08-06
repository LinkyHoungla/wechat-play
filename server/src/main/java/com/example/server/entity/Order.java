package com.example.server.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private String id;
    private String sid;
    private String uid;
    private BigDecimal totalPrice;
    private Date startTime;
    private Date endTime;
    private Integer serveDuration;
    private String status;
    private Date createTime;
    private Date updateTime;
}
