package com.example.server.controller;

import com.example.server.constant.ApiError;
import com.example.server.dto.param.RoleParam;
import com.example.server.entity.Role;
import com.example.server.exception.ApiException;
import com.example.server.service.impl.PermissionServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionServiceImpl permissionService;

    @GetMapping("/role")
    public ApiResponse<PageQuery<Role>> getAdminByPage(String query,
                                                       @RequestParam(defaultValue = "1")Integer pageNum,
                                                       @RequestParam(defaultValue = "10")Integer pageSize){
        return ApiResponse.success(permissionService.getRoleList(query, pageNum, pageSize));
    }

    @PostMapping("/role")
    public ApiResponse<Integer> addAdmin(@RequestBody @Valid RoleParam param) {
        return ApiResponse.success(permissionService.addRole(param));
    }

    @PutMapping("/role/{id}")
    public ApiResponse<Integer> updateAdmin(@PathVariable("id")Integer id, @RequestBody @Valid RoleParam param) {
        if (id == null) {
            throw new ApiException(ApiError.E460);
        }

        return ApiResponse.success(permissionService.updateRole(id, param));
    }

    @DeleteMapping("/role/{id}")
    public ApiResponse<Integer> deleteAdmin(@PathVariable("id")Integer id) {
        if (id == null) {
            throw new ApiException(ApiError.E460);
        }

        return ApiResponse.success(permissionService.deleteRole(id));
    }
}
