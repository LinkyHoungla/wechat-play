package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private Integer rid;
    private String name;
    private String avatar;
    private String status;
    private Date updateTime;
    private Date createTime;
}
