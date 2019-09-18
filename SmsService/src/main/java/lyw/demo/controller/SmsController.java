package lyw.demo.controller;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SmsController {

    private String message;

    @GetMapping("sms")
    public String getSms(Integer id, HttpServletRequest httpServletRequest){
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(message);
        return "smsService" + id + " smsPort" + httpServletRequest.getLocalPort() + " zuulPort" + message;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms-8002",durable = "true"),
            exchange = @Exchange(
                    value = "Service",type = ExchangeTypes.DIRECT
            ),
            key = "sms"
    ))
    public void setMessage(String message){
        this.message = message;
    }
}
