package xyz.xy718.pre_attend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PreAttendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreAttendApplication.class, args);
    }

}
