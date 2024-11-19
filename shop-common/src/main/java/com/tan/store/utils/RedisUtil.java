package com.tan.store.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil implements ApplicationContextAware {

    private static StringRedisTemplate redisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RedisUtil.redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }

    public  boolean put(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public  boolean putExpire(String key, String value, long expire) {
        try {
            redisTemplate.opsForValue().set(key, value,expire, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

}
