package xyz.xy718.attend_recorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AttendRecorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendRecorderApplication.class, args);
    }

}
