package com.example.server.controller;

import com.example.server.annotation.RequirePermission;
import com.example.server.constant.OrderEnum;
import com.example.server.dto.param.BalanceParam;
import com.example.server.dto.param.RequireParam;
import com.example.server.dto.param.StatusParam;
import com.example.server.entity.Order;
import com.example.server.entity.Wallet;
import com.example.server.service.impl.OrderServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import com.example.server.annotation.EnumValue;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("/page")
    @RequirePermission
    public ApiResponse<PageQuery<Order>> getOrderByPage(@Length(max = 20, message = "长度超限") String query,
                                                                @EnumValue(enumClass = OrderEnum.class, ableNull = true) String tag,
                                                                @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                                @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(orderService.getOrderList(query, tag, pageNum, pageSize));
    }

    @PostMapping
    @RequirePermission
    public ApiResponse<Integer> addOrder(@RequestBody @Valid RequireParam param) {
        return ApiResponse.success(orderService.addOrder(param));
    }

    @PutMapping
    @RequirePermission
    public ApiResponse<Integer> updateStatus(@RequestBody @Valid StatusParam param) {
        return ApiResponse.success(orderService.updateStatus(param.getId(), param.getStatus()));
    }

    @GetMapping("/wallet/page")
    @RequirePermission
    public ApiResponse<PageQuery<Wallet>> getBalanceByPage(@Length(max = 20, message = "长度超限") String query,
                                                           @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                           @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(orderService.getWalletList(query, pageNum, pageSize));
    }

    @PutMapping("/wallet")
    @RequirePermission
    public ApiResponse<Integer> updateBalance(@RequestBody @Valid BalanceParam param) {
        return ApiResponse.success(orderService.updateWallet(param));
    }

}
