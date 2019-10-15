package cn.knowimage.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration aa =  registry.addInterceptor(new firstInterceptor());
        aa.addPathPatterns("/*");//对所有请求拦截
        aa.excludePathPatterns("/index");//只有对/index不进行拦截
        aa.excludePathPatterns("/insert");
    }
}