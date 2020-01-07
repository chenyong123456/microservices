package cn.knowimage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(scanBasePackages = {"cn.knowimage"})
@EntityScan(basePackages = "cn.knowimage.pojo")
@MapperScan(value = "cn.knowimage.mapper")
@EnableAsync
//@EnableDiscoveryClient
//@EnableCircuitBreaker
//启动类
public class ClincialPathwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClincialPathwayApplication.class);
    }


}



