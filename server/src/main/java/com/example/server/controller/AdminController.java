package com.example.server.controller;

import com.example.server.dto.LoginParam;
import com.example.server.dto.CurrentAdmin;
import com.example.server.model.ApiResponse;
import com.example.server.service.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceImpl adminService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginParam loginParam, HttpServletRequest request) {
        return ApiResponse.success(adminService.login(loginParam.getUsername(), loginParam.getPassword(), request.getRemoteAddr()));
    }

    @GetMapping("/logout")
    public ApiResponse<Boolean> logout(HttpServletRequest request) {
        return ApiResponse.success(adminService.logout(request));
    }

    @GetMapping("/current")
    public ApiResponse<CurrentAdmin> getCurrentAdmin(HttpServletRequest request) {
        Integer id = Integer.parseInt((String) request.getAttribute("id"));
        Integer rid = (Integer) request.getAttribute("rid");

        System.out.println("id:" + id);
        System.out.println("rid:" + rid);

        return ApiResponse.success(adminService.getCurrentAdmin(id, rid));
    }
}
