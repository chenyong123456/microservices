package cn.knowimage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"cn.knowimage"})
@EntityScan(basePackages = "cn.knowimage.pojo")
@MapperScan(value = "cn.knowimage.mapper")
@EnableDiscoveryClient
/*@MapperScan(basePackages = "cn.knowiamge.*",sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate",annotationClass = Repository.class
)*/
//添加了extend及以后的内容，重写了方法
public class ClincialPathwayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClincialPathwayApplication.class, args);
        //System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");

    }


}
