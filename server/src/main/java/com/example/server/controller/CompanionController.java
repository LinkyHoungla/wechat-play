package com.example.server.controller;

import com.example.server.constant.GameEnum;
import com.example.server.constant.ServiceEnum;
import com.example.server.constant.StatusEnum;
import com.example.server.dto.param.GameParam;
import com.example.server.dto.param.ServiceParam;
import com.example.server.dto.param.StatusParam;
import com.example.server.dto.param.USidParam;
import com.example.server.entity.Companion;
import com.example.server.entity.Game;
import com.example.server.entity.Service;
import com.example.server.service.impl.CompanionServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import com.example.server.util.validator.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/companion")
@RequiredArgsConstructor
@Validated
public class CompanionController {
    private final CompanionServiceImpl companionService;

    @GetMapping("/page")
    public ApiResponse<PageQuery<Companion>> getCompanionByPage(@Length(max = 20, message = "长度超限") String query,
                                                                @EnumValue(enumClass = StatusEnum.class, ableNull = true) String tag,
                                                                @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                                @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(companionService.getCompanionList(query, tag, pageNum, pageSize));
    }

    @PostMapping
    public ApiResponse<Integer> addCompanion(@RequestBody @Valid USidParam param) {
        return ApiResponse.success(companionService.addCompanion(param));
    }

    @PutMapping
    public ApiResponse<Integer> updateCompanion(@RequestBody @Validated(value = ValidGroup.Type.Goods.class) StatusParam param) {
        return ApiResponse.success(companionService.updateStatus(Integer.parseInt(param.getId()), param.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteCompanion(@PathVariable("id") @Min(0) Integer id) {
        return ApiResponse.success(companionService.deleteCompanion(id));
    }

    @GetMapping("/service/page")
    public ApiResponse<PageQuery<Service>> getServiceByPage(@Length(max = 20, message = "长度超限") String query,
                                                              @EnumValue(enumClass = ServiceEnum.class, ableNull = true) String tag,
                                                              @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                              @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(companionService.getServiceList(query, tag, pageNum, pageSize));
    }

    @PostMapping("/service")
    public ApiResponse<Integer> addService(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ServiceParam param) {
        return ApiResponse.success(companionService.addService(param));
    }

    @PutMapping("/service")
    public ApiResponse<Integer> updateService(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ServiceParam param) {
        return ApiResponse.success(companionService.updateService(param));
    }

    @DeleteMapping("/service/{id}")
    public ApiResponse<Integer> deleteService(@PathVariable("id") @Min(0) Integer id) {
        return ApiResponse.success(companionService.deleteService(id));
    }

    @GetMapping("/game/page")
    public ApiResponse<PageQuery<Game>> getGameByPage(@Length(max = 20, message = "长度超限") String query,
                                                      @EnumValue(enumClass = GameEnum.class, ableNull = true) String tag,
                                                         @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                         @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(companionService.getGameList(query, tag, pageNum, pageSize));
    }

    @PostMapping("/game")
    public ApiResponse<Integer> addGame(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) GameParam param) {
        return ApiResponse.success(companionService.addGame(param));
    }

    @PutMapping("/game")
    public ApiResponse<Integer> updateGame(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) GameParam param) {
        return ApiResponse.success(companionService.updateGame(param));
    }

    @DeleteMapping("/game/{id}")
    public ApiResponse<Integer> deleteGame(@PathVariable("id") @Min(0) Integer id) {
        return ApiResponse.success(companionService.deleteGame(id));
    }
}
