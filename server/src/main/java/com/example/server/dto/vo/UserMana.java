package com.example.server.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserMana {
    private String id;
    private String role;
    private String wxid;
    private String phone;
    private String status;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String ip;
    private String loginTime;
}
