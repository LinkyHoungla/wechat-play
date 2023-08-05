package com.example.server.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String id;
    private String role;
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

    private String username;
    private String ip;
    private String loginTime;
}
