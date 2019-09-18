package lyw.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RabbitMQApplication {
    @Autowired
    private AmqpTemplate amqpTemplate;


    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class);
    }

    @RequestMapping("send")
    public String sendMessage(String message){
        amqpTemplate.convertAndSend("Service","sms",message);
        return "true";
    }
}
