package com.example.server.dto.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PermissionTree {
    private Integer id;
    private Integer pid;
    private String name;
    private String path;
    private String icon;
    private Integer level;
    private Date createTime;
    private Date updateTime;
    private List<PermissionTree> children;
}
