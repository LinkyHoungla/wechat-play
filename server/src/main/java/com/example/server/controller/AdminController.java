package com.example.server.controller;

import com.example.server.constant.ApiError;
import com.example.server.dto.vo.AdminInfo;
import com.example.server.dto.vo.CurrentAdmin;
import com.example.server.dto.param.AdminParam;
import com.example.server.dto.param.LoginParam;
import com.example.server.exception.ApiException;
import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.JwtUtil;
import com.example.server.util.PageQuery;
import com.example.server.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {
    private final AdminServiceImpl adminService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody @Valid LoginParam loginParam, HttpServletRequest request) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        String ip = ValidateUtil.getClientIpAddress(request);

        return ApiResponse.success(adminService.login(username, password, ip));
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
    public ApiResponse<PageQuery<AdminInfo>> getAdminByPage(@Length(max = 20, message = "长度超限") String query,
                                                            @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                            @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(adminService.getAdminList(query, pageNum, pageSize));
    }

    @PostMapping
    public ApiResponse<Integer> addAdmin(@RequestBody @Valid AdminParam param) {
        return ApiResponse.success(adminService.addAdmin(param));
    }

    @PutMapping("/{id}")
    public ApiResponse<Integer> updateAdmin(@PathVariable("id")Integer id, @RequestBody @Valid AdminParam param) {
        return ApiResponse.success(adminService.updateAdmin(id, param));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteAdmin(@PathVariable("id")Integer id) {
        if (id == null) {
            throw new ApiException(ApiError.E460);
        }

        return ApiResponse.success(adminService.deleteAdmin(id));
    }
}
