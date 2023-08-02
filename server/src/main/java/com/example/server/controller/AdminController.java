package com.example.server.controller;

import com.example.server.constant.ApiError;
import com.example.server.dto.AdminInfo;
import com.example.server.dto.CurrentAdmin;
import com.example.server.dto.LoginParam;
import com.example.server.exception.ApiException;
import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.JwtUtil;
import com.example.server.util.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        String token = JwtUtil.extractTokenFromRequest(request);
        if (token == null)
            throw new ApiException(ApiError.E453);

        return ApiResponse.success(adminService.logout(token));
    }

    @GetMapping("/current")
    public ApiResponse<CurrentAdmin> getCurrentAdmin(HttpServletRequest request) {
        Integer id = Integer.parseInt((String) request.getAttribute("id"));
        Integer rid = (Integer) request.getAttribute("rid");

        return ApiResponse.success(adminService.getCurrentAdmin(id, rid));
    }

    @GetMapping("/page")
    public ApiResponse<PageQuery<AdminInfo>> getAdminByPage(String query,
                                                            @RequestParam(defaultValue = "1")Integer pageNum,
                                                            @RequestParam(defaultValue = "10")Integer pageSize){
        return ApiResponse.success(adminService.getAdminList(query, pageNum, pageSize));
    }

    @PutMapping("/{id}")
    public ApiResponse<Integer> updateAdmin(@PathVariable("id")Integer id, @RequestBody Map<String, Object> map) {
        return ApiResponse.success(adminService.updateAdmin(id, map));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteAdmin(@PathVariable("id")Integer id) {
        if (id == null) {
            throw new ApiException(ApiError.E460);
        }

        return ApiResponse.success(adminService.deleteAdmin(id));
    }
}
