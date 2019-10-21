package lyw.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class,args);
    }
    @Value("${foo}")
    String foo;
    @Value("${pl}")
    String pl;
    @RequestMapping("/foo")
    public String hi(){
        System.out.println(foo);
        System.out.println(pl);
        return foo+pl;
    }
}
