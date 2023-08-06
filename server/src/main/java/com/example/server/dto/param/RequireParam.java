package com.example.server.dto.param;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class RequireParam {
    private String uid;
    private String sid;
    private String serveType;
    private String gender;
    private List<String> voice;
    private List<String> nature;
    private String detail;
    private Instant estimatedStart;
    private Integer estimatedDuration;
    private BigDecimal priceLow;
    private BigDecimal priceHigh;
    private String note;
}
