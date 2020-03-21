package com.test.idgenerate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 
 * @author yuzhou
 * @date 2017年9月1日
 * @time 上午11:52:09
 *
 */
@Configuration
public class IdGenConfiguration {
    
    @Bean
    public RedisTemplate<String, Long> stringLongRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate< String, Long > template =  new RedisTemplate< String, Long >();
        template.setConnectionFactory( factory );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Long >( Long.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Long >( Long.class ) );
        return template;
    }
    
    @Bean
    public IdGenerator idGenerator(RedisTemplate<String, Long> stringLongRedisTemplate) {
        return new IdGenerator(stringLongRedisTemplate);
    }

}
