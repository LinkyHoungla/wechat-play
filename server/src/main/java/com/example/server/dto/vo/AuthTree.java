package com.example.server.dto.vo;

import lombok.Data;

import java.util.List;

@Data
public class AuthTree {
    private Integer id;
    private String name;
    private Integer pid;
    private List<AuthTree> children;
}
