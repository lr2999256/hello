package com.test.lock;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @date : 2020/3/20 10:07
 **/
@Component
public class RedisLock {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final long SLEEP_TIME = 10;

    public boolean tryLock(String key, long lockTime, long waitTime, TimeUnit unit) throws InterruptedException{
        long waitTimeMillis = unit.toMillis(waitTime);
        long lockTimeSecond = unit.toSeconds(lockTime);
        long waitAlready = 0;
        while (waitAlready < waitTimeMillis && !stringRedisTemplate.opsForValue().setIfAbsent(key, "1")) {
            waitAlready += SLEEP_TIME;
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        if(waitAlready >= waitTimeMillis){
            return false;
        }
        stringRedisTemplate.expire(key, lockTime, unit);
        return true;
    }

    public void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
