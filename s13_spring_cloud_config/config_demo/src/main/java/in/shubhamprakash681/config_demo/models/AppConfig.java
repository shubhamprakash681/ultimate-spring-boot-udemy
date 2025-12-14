package in.shubhamprakash681.config_demo.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String welcomeMessage;
    private String encryptedMessage;
    private int maxLoginAttempts;
    private FeatureToggles featureToggles;
}

@Data
class FeatureToggles {
    private boolean newDashboard;
    private boolean betaFeatures;
}