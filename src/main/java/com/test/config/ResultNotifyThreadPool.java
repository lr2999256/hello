package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author : Rui
 * @date : 2019/8/7 16:59
 **/
@Configuration
public class ResultNotifyThreadPool {

    @Bean(name = "ctoResultNotifyThreadPool")
    public ExecutorService getCtoQueryResultThreadPool(){
        return new ThreadPoolExecutor(7, 10,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), new ThreadPoolExecutor.AbortPolicy());
    }

}
