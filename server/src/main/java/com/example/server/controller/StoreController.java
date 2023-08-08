package com.example.server.controller;

import com.example.server.constant.StatusEnum;
import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.StoreParam;
import com.example.server.entity.Balance;
import com.example.server.entity.Store;
import com.example.server.service.impl.StoreServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import com.example.server.util.ValidateUtil;
import com.example.server.annotation.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@Validated
public class StoreController {
    private final StoreServiceImpl storeService;

    @GetMapping("/page")
    public ApiResponse<PageQuery<Store>> getStoreByPage(@Length(max = 20, message = "长度超限") String query,
                                                        @EnumValue(enumClass = StatusEnum.class, ableNull = true) String tag,
                                                        @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                        @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(storeService.getStoreList(query, tag, pageNum, pageSize));
    }

    @PostMapping
    public ApiResponse<Integer> addStore(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) StoreParam param) {
        return ApiResponse.success(storeService.addStore(param));
    }

    @PutMapping
    public ApiResponse<Integer> updateStore(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) StoreParam param) {
        return ApiResponse.success(storeService.updateStore(param));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteStore(@PathVariable("id") @Pattern(regexp = ValidateUtil.UID) String id) {
        return ApiResponse.success(storeService.deleteStore(id));
    }

    @GetMapping("/balance/page")
    public ApiResponse<PageQuery<Balance>> getBalanceByPage(@Length(max = 20, message = "长度超限") String query,
                                                            @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                            @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(storeService.getBalanceList(query, pageNum, pageSize));
    }

    @PostMapping("/balance")
    public ApiResponse<Integer> addBalance(@RequestBody @Valid BalanceParam param) {
        return ApiResponse.success(storeService.addBalance(param));
    }

    @PutMapping("/balance")
    public ApiResponse<Integer> updateBalance(@RequestBody @Valid BalanceParam param) {
        return ApiResponse.success(storeService.updateBalance(param));
    }
}
