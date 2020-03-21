//package com.test.druid;
//
//import com.alibaba.druid.filter.logging.Log4jFilter;
//import com.alibaba.druid.filter.logging.Slf4jLogFilter;
//import com.alibaba.druid.filter.stat.StatFilter;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author : Rui
// * @date : 2020/3/15 15:50
// **/
//@Configuration
//public class DruidMonitorAutoConfiguration {
//    public static final String LOGIN_USER_NAME = "rui";
//    public static final String LOGIN_PASSWORD = "rui";
//    public static final String STAT_SERVLET_URL_MAPPINGS = "/druid/*";
//    public static final String STAT_FILTER_URL_PATTERNS = "/*";
//
//
//    /**
//     * 注册druidServlet
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean druidServletRegistrationBean() {
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//        servletRegistrationBean.setServlet(new StatViewServlet());
//        servletRegistrationBean.addUrlMappings(STAT_SERVLET_URL_MAPPINGS);
//        servletRegistrationBean.addInitParameter("loginUsername", LOGIN_USER_NAME);
//        servletRegistrationBean.addInitParameter("loginPassword", LOGIN_PASSWORD);
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean duridFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        Map<String, String> initParams = new HashMap<>();
//        //设置忽略请求
//        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.setInitParameters(initParams);
//        filterRegistrationBean.addUrlPatterns(STAT_FILTER_URL_PATTERNS);
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
//        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public StatFilter statFilter(){
//        StatFilter statFilter = new StatFilter();
//        statFilter.setMergeSql(true);
//        statFilter.setSlowSqlMillis(10000);
//        statFilter.setLogSlowSql(true);
//        return statFilter;
//    }
//
//    @Bean
//    public Log4jFilter slf4jLogFilter(){
//        return new Log4jFilter();
//    }
//}
