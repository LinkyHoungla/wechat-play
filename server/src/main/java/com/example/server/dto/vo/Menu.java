package com.example.server.dto.vo;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;
    private String name;
    private String path;
    private String icon;
    private List<Menu> children;
}
