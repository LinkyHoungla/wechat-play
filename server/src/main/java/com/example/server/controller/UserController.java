package com.example.server.controller;

import com.example.server.constant.ApiError;
import com.example.server.dto.param.LoginParam;
import com.example.server.dto.param.StatusParam;
import com.example.server.dto.param.UserInfoParam;
import com.example.server.dto.param.UserLogParam;
import com.example.server.dto.vo.UserInfoVo;
import com.example.server.dto.vo.UserMana;
import com.example.server.entity.UserInfo;
import com.example.server.exception.ApiException;
import com.example.server.service.impl.UserServiceImpl;
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
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody @Valid LoginParam loginParam, HttpServletRequest request) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        String ip = ValidateUtil.getClientIpAddress(request);

        return ApiResponse.success(userService.login(username, password, ip));
    }

    @GetMapping("/logout")
    public ApiResponse<Boolean> logout(HttpServletRequest request) {
        String token = JwtUtil.extractTokenFromRequest(request);
        if (token == null)
            throw new ApiException(ApiError.E453);

        return ApiResponse.success(userService.logout(token));
    }

    @GetMapping("/page")
    public ApiResponse<PageQuery<UserMana>> getUserByPage(@Length(max = 20, message = "长度超限") String query,
                                                          @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                          @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(userService.getUserList(query, pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserInfoVo> getUserInfo(@PathVariable("id") @Pattern(regexp = ValidateUtil.UID) String id) {
        return ApiResponse.success(userService.getUserInfo(id));
    }

    @GetMapping("/mana/{id}")
    public ApiResponse<UserInfo> getUserInfoMana(@PathVariable("id") @Pattern(regexp = ValidateUtil.UID) String id) {
        return ApiResponse.success(userService.getUserInfoMana(id));
    }

    @PostMapping
    public ApiResponse<Integer> addUser(@RequestBody @Valid UserLogParam param) {
        return ApiResponse.success(userService.addUser(param));
    }

    @PutMapping("/status")
    public ApiResponse<Integer> updateStatus(@RequestBody @Valid StatusParam param) {
        return ApiResponse.success(userService.updateStatus(param));
    }

    @PutMapping
    public ApiResponse<Integer> updateUser(@RequestBody @Valid UserInfoParam param) {
        return ApiResponse.success(userService.updateUserInfo(param));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteUser(@PathVariable("id") @Pattern(regexp = ValidateUtil.UID) String id) {
        return ApiResponse.success(userService.deleteUser(id));
    }
}
