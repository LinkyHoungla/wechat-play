package com.example.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String name;
    private String gender;
    private String avatar;
    private String location;
    private Integer age;
    private Date birth;
    private Date createTime;
    private Date updateTime;
}
