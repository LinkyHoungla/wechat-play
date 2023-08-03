package com.example.server.dto.vo;

import com.example.server.entity.Admin;
import lombok.Data;

import java.util.Date;

@Data
public class AdminInfo {
    private Integer id;
    private Integer rid;
    private String name;
    private String avatar;
    private String status;
    private Date updateTime;
    private Date createTime;
    private String username;
    private String role;
    private String ip;
    private Date loginTime;
}
