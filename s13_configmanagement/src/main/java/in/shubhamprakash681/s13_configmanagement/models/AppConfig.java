package in.shubhamprakash681.s13_configmanagement.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String welcomeMessage;
    private int maxLoginAttempts;
    private FeatureToggles featureToggles;
}

@Data
class FeatureToggles {
    private boolean newDashboard;
    private boolean betaFeatures;
}