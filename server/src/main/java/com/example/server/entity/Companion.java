package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Companion {
    private Integer id;
    private String uid;
    private String sid;
    private String status;
    private Date createTime;
    private Date updateTime;
}
