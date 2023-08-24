package com.example.server.controller;

import com.example.server.annotation.RequirePermission;
import com.example.server.dto.param.PermissionParam;
import com.example.server.dto.param.RoleParam;
import com.example.server.dto.vo.AuthTree;
import com.example.server.dto.vo.PermissionTree;
import com.example.server.dto.vo.RoleList;
import com.example.server.entity.Role;
import com.example.server.service.impl.PermissionServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import com.example.server.util.validator.ValidGroup;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionServiceImpl permissionService;

    @GetMapping("/role")
    @RequirePermission
    public ApiResponse<PageQuery<Role>> getRoleByPage(@Length(max = 20, message = "长度超限") String query,
                                                       @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                       @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(permissionService.getRoleList(query, pageNum, pageSize));
    }

    @GetMapping("/roles")
    @RequirePermission
    public ApiResponse<List<RoleList>> getRoles() {
        return ApiResponse.success(permissionService.getRoles());
    }

    @PostMapping("/role")
    @RequirePermission
    public ApiResponse<Integer> addRole(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) RoleParam param) {
        return ApiResponse.success(permissionService.addRole(param));
    }

    @PutMapping("/role")
    @RequirePermission
    public ApiResponse<Integer> updateRole(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) RoleParam param) {
        return ApiResponse.success(permissionService.updateRole(param));
    }

    @DeleteMapping("/role/{id}")
    @RequirePermission
    public ApiResponse<Integer> deleteRole(@PathVariable("id") @Min(1) Integer id) {
        return ApiResponse.success(permissionService.deleteRole(id));
    }

    @GetMapping("/page")
    @RequirePermission
    public ApiResponse<PageQuery<PermissionTree>> getPermissionTree(@Length(max = 20, message = "长度超限") String query,
                                                                    @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                                    @RequestParam(defaultValue = "10") @Min(1) Integer pageSize) {
        return ApiResponse.success(permissionService.getPermissionTree(query, pageNum, pageSize));
    }

    @PostMapping
    @RequirePermission
    public ApiResponse<Integer> addPermission(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) PermissionParam param) {
        return ApiResponse.success(permissionService.addPermission(param));
    }

    @PutMapping
    @RequirePermission
    public ApiResponse<Integer> updatePermission(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) PermissionParam param) {
        return ApiResponse.success(permissionService.updatePermission(param));
    }

    @DeleteMapping("/{id}")
    @RequirePermission
    public ApiResponse<Integer> deletePermission(@PathVariable("id") @Min(1) Integer id) {
        return ApiResponse.success(permissionService.deletePermission(id));
    }

    @PutMapping("/auth/{id}")
    @RequirePermission
    public ApiResponse<Integer> updateAuth(@PathVariable("id") @Min(1) Integer id,
                                           @RequestBody @NotEmpty List<Integer> auth) {
        return ApiResponse.success(permissionService.auth(id, auth));
    }

    @GetMapping("/rights/{id}")
    @RequirePermission
    public ApiResponse<List<Integer>> getAuthList(@PathVariable("id") @Min(0) Integer id){
        return ApiResponse.success(permissionService.getAuthList(id));
    }

    @GetMapping("/auth/{level}")
    @RequirePermission
    public ApiResponse<List<AuthTree>> getAuth(@PathVariable("level") @Min(1) Integer level, @RequestParam(name = "id", required = false) Integer id) {

        if (id != null && id < 0) {
            throw new IllegalArgumentException("Id 值非法");
        }

        return ApiResponse.success(permissionService.getAuthTree(level, id));
    }

    @DeleteMapping("/auth/{rid}/{pid}")
    @RequirePermission
    public ApiResponse<Integer> deleteAuth(@PathVariable("rid") @Min(1) Integer rid,  @PathVariable("pid") @Min(1) Integer pid) {
        return ApiResponse.success(permissionService.deleteAuth(rid, pid));
    }

}
