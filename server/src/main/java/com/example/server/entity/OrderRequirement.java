package com.example.server.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequirement {
    private String id;
    private String uid;
    private String sid;
    private String serveType;
    private String gender;
    private List<String> voice;
    private List<String> nature;
    private String detail;
    private Integer estimatedDuration;
    private Instant estimatedStart;
    private BigDecimal priceLow;
    private BigDecimal priceHigh;
    private String note;
    private Date createTime;
    private Date updateTIme;

}
