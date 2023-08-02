package com.example.server.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageQuery<T> {
    private Long total;
    private List<T> list;

    public PageQuery (PageInfo<T> page) {
        this.total = page.getTotal();
        this.list = page.getList();
    }
}
