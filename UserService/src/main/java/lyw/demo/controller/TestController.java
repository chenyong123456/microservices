package lyw.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lyw.demo.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private TestClient testClient;

    @GetMapping("test")
    public String getString(){
        List<ServiceInstance> list = discoveryClient.getInstances("Smsservice");
        System.out.println(list.size());
        list.forEach((serviceInstance)->{
            System.out.println(serviceInstance.getPort());
        });
        return "true";
    }

    @GetMapping(value = "sms")
    public String getSms(Integer id){
        return testClient.test(id);
    }
}
