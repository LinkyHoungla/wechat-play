package com.example.server.dto.vo;

import com.example.server.dto.vo.Menu;
import lombok.Data;

import java.util.List;

@Data
public class CurrentAdmin {
    private String name;
    private String role;
    private String avatar;
    private List<Menu> menus;
    private List<Integer> permissions;

    public CurrentAdmin(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
