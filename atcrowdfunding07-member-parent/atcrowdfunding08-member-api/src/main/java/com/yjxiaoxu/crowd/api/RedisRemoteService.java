package com.yjxiaoxu.crowd.api;

import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:RedisRemoteService
 * Package:com.yjxiaoxu.crowd.api
 * Description:
 *
 * @Date:2022/2/9 22:00
 * @Author:小许33058485@qq.com
 */
@FeignClient(value = "crowd-member-redis")
@Component
public interface RedisRemoteService {
    // 缓存key, value的方法
    @RequestMapping("/redis/set/cached/key/and/value")
    public ResultEntity<String> setRedisCachedKeyAndValue(@RequestParam("key") String key);

    // 缓存key,value，并设置缓存过时时间的方法
    @RequestMapping("/redis/set/cached/key/and/value/timeout")
    public ResultEntity<String> setRedisCachedKeyAndValueTimeout(@RequestParam("key") String key,
                                                                 @RequestParam("value") String value,
                                                                 @RequestParam("timeout") long timeout,
                                                                 @RequestParam("timeUnit") TimeUnit timeUnit);

    // 根据key从缓存中获取value
    @RequestMapping("/redis/get/cached/value/by/key")
    public ResultEntity<String> getRedisCachedValueByKey(@RequestParam("key") String key);

    // 根据key从缓存中删除value
    @RequestMapping("/redis/remove/cached/value/by/key")
    public ResultEntity<String> removeRedisCachedValueByKey(@RequestParam("key") String key);
}
