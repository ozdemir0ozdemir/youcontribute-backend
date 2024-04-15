package youcontribute.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationProperties {

    private Long importFrequency;
    private Long challengeFrequency;

    private OneSignalProperties oneSignalProperties;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OneSignalProperties {


        private String apiAuthKey;
        private String appId;

        private String templateId;
    }
}