package lyw.demo.client.clientImpl;

import lyw.demo.client.TestClient;
import org.springframework.stereotype.Component;

@Component
public class TestClientImpl implements TestClient {
    @Override
    public String test(Integer id) {
        return "sms error";
    }
}
