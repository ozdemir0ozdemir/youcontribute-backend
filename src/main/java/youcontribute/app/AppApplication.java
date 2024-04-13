package youcontribute.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        // since YYYY-MM-DDTHH:MM:SSZ
//        ZonedDateTime date = ZonedDateTime.now();
//        System.out.println(date);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    // TODO: header "Link" parser
    // <https://api.github.com/repositories/86742497/issues?page=2>; rel="prev", <https://api.github.com/repositories/86742497/issues?page=4>; rel="next", <https://api.github.com/repositories/86742497/issues?page=5>; rel="last", <https://api.github.com/repositories/86742497/issues?page=1>; rel="first"
}
