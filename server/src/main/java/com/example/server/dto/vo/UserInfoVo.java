package com.example.server.dto.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo {
    private String name;
    private String gender;
    private String avatar;
    private String location;
    private Integer age;
    private Date birth;
}
