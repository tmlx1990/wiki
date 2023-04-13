package com.siants.wiki.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public boolean validateRepeat(String key, long second) {
        if (redisTemplate.hasKey(key)) {
            log.info("key已存在 : {}", key);
            return false;
        } else {
            log.info("key不存在，放入 : {}, 过期 {} 秒", key, second);
            redisTemplate.opsForValue().set(key, key, second, TimeUnit.SECONDS);
            return true;
        }
    }
}
