package youcontribute.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "github")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubProperties {

    @Value("${github.token}")
    private String token;

    @Value("${github.api-url}")
    private String apiUrl;
}