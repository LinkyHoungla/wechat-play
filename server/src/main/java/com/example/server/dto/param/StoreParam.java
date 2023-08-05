package com.example.server.dto.param;

import lombok.Data;

import java.util.Date;

@Data
public class StoreParam {
    private String id;
    private String name;
    private String desc;
    private String oid;
    private String owner;
    private String phone;
}
