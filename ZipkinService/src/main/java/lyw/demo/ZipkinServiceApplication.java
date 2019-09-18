package lyw.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

import javax.sql.DataSource;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class  ZipkinServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServiceApplication.class);
    }
}
