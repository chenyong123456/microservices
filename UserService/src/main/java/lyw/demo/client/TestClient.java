package lyw.demo.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lyw.demo.client.clientImpl.TestClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SmsService",fallback = TestClientImpl.class)

public interface TestClient {

    @GetMapping("sms")
    public String test(@RequestParam("id") Integer id);
}
