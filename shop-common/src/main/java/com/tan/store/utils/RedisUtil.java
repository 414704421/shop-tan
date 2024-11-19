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

    public boolean setWithExpire(String key, String value, Long seconds) {
        return setWithExpire(key, value, seconds, TimeUnit.SECONDS);
    }

    public  boolean setWithExpire(String key, String value, Long expire, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value,expire, timeUnit);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean delKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

}
