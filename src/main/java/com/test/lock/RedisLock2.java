package com.test.lock;

import com.test.idgenerate.LongRedisSerializer;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @date : 2020/3/20 10:07
 **/
@Component
public class RedisLock2 {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final long SLEEP_TIME = 10;

    private static final String REDIS_LOCK_SCRIPT = "local key = ARGV[1]\n" + "local value = ARGV[2]\n" + "local ttl = ARGV[3]\n"
        + "local ok = redis.call('setnx', key, value)\n" + "if ok == 1 then\n" + "redis.call('expire', key, ttl)\n" + "end\n"
        + "return ok";

    public boolean tryLock(String key, long lockTime, long waitTime, TimeUnit unit) throws InterruptedException{
        long waitTimeMillis = unit.toMillis(waitTime);
        long lockTimeSecond = unit.toSeconds(lockTime);
        long waitAlready = 0;
        while (waitAlready < waitTimeMillis && 1!=this.executeLuaLock(key, lockTimeSecond)) {
            waitAlready += SLEEP_TIME;
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        }
        if(waitAlready >= waitTimeMillis){
            return false;
        }
        stringRedisTemplate.expire(key, lockTime, unit);
        return true;
    }

    private Long executeLuaLock(String key, long lockTimeSecond){
        return stringRedisTemplate.execute(new DefaultRedisScript<>(REDIS_LOCK_SCRIPT, Long.class), new StringRedisSerializer(),
                                           new LongRedisSerializer(), null,key,"1",String.valueOf(lockTimeSecond));
    }

    public void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
