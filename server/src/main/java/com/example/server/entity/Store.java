package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Store {
    private String id;
    private String name;
    private String avatar;
    private String desc;
    private String owner;
    private String oid;
    private String phone;
    private String status;
    private Date createTime;
    private Date updateTime;
}
