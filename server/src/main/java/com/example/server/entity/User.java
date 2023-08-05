package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private Integer rid;
    private String nickname;
    private String wxid;
    private String phone;
    private String gender;
    private String avatar;
    private String location;
    private Integer age;
    private Date birth;
    private String status;
    private Date createTime;
    private Date updateTime;
}
