package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;
    private String name;
    private String desc;
    private Date createTime;
    private Date updateTime;
}
