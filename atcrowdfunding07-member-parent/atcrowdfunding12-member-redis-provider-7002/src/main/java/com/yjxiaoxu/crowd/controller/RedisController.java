package com.yjxiaoxu.crowd.controller;

import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:RedisController
 * Package:com.yjxiaoxu.crowd.controller
 * Description:
 *
 * @Date:2022/2/9 21:26
 * @Author:小许33058485@qq.com
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    // 缓存key, value的方法
    @RequestMapping("/redis/set/cached/key/and/value")
    public ResultEntity<String> setRedisCachedKeyAndValue(@RequestParam("key") String key,
                                                          @RequestParam("value") String value) {

        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);

            // 程序执行到此处表示缓存成功，返回成果结果（不带数据）
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            e.printStackTrace();

            // 程序执行到此表示缓存失败，返回失败结果并返回异常信息
            return ResultEntity.failed(e.getMessage());
        }

    }

    // 缓存key,value，并设置缓存过时时间的方法
    @RequestMapping("/redis/set/cached/key/and/value/timeout")
    public ResultEntity<String> setRedisCachedKeyAndValueTimeout(@RequestParam("key") String key,
                                                                 @RequestParam("value") String value,
                                                                 @RequestParam("timeout") long timeout,
                                                                 @RequestParam("timeUnit") TimeUnit timeUnit) {

        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value, timeout, timeUnit);

            // 程序执行到此处表示缓存成功，返回成果结果（不带数据）
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            e.printStackTrace();

            // 程序执行到此表示缓存失败，返回失败结果并返回异常信息
            return ResultEntity.failed(e.getMessage());
        }
    }

    // 根据key从缓存中获取value
    @RequestMapping("/redis/get/cached/value/by/key")
    public ResultEntity<String> getRedisCachedValueByKey(@RequestParam("key") String key) {

        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String value = operations.get(key);

            // 程序执行到此处表示成功，返回成果结果（带数据）
            return ResultEntity.successWithDate(value);
        } catch (Exception e) {
            e.printStackTrace();

            // 程序执行到此表示失败，返回失败结果并返回异常信息
            return ResultEntity.failed(e.getMessage());
        }
    }
    // 根据key从缓存中删除value
    @RequestMapping("/redis/remove/cached/value/by/key")
    public ResultEntity<String> removeRedisCachedValueByKey(@RequestParam("key") String key) {

        try {
           redisTemplate.delete(key);

            // 程序执行到此处表示删除缓存成功，返回成果结果（不带数据）
            return ResultEntity.successWithOutDate();
        } catch (Exception e) {
            e.printStackTrace();

            // 程序执行到此表示删除缓存失败，返回失败结果并返回异常信息
            return ResultEntity.failed(e.getMessage());
        }
    }
}
