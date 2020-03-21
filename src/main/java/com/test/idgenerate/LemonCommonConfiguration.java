package com.test.idgenerate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LemonCommonConfiguration {
    
    @Bean
    public ExtensionLoader extensionLoader() {
        return new ExtensionLoader();
    }

}
