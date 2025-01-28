package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("AdminShopController")
@RequestMapping("/admin/shop")
@Api("Shop Controller")
@Slf4j
public class ShopController {

    public static final String KEY="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("Set shop status")
    public Result setShopStatus(@PathVariable Integer status){
        log.info("set status of shop is: {}", status == 1 ? "opening" : "closed");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("Get shop status")
    public Result<Integer> getShopStatus(){
        Integer status =(Integer) redisTemplate.opsForValue().get(KEY);
        log.info("get shop status: {}", status == 1 ? "opening" : "closed");
        return Result.success(status);
    }
}
