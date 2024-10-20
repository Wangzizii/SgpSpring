package com.wz.config;

import com.wz.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
//                .allowCredentials(true);
    }
    @Bean("taskExecutor")
    public Executor taskExecutor () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);

        executor.setMaxPoolSize(15);

        executor.setQueueCapacity(200);
        // 允许线程的空闲时间60秒：
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀：
        executor.setThreadNamePrefix("taskExecutor-");
        /*
        线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，
        executor.setAwaitTerminationSeconds(600);
        return executor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/index.html","/user/active","/user/active/**",
        "/car/**","/user/reactvie","/user/checkmfa","/user/twostep","/user/forget/**","/user/forget");
//        registry.addInterceptor(loginInterceptor).excludePathPatterns("/**");
    }
}
