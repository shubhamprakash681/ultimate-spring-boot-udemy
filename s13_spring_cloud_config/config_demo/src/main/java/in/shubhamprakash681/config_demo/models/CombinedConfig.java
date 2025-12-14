package in.shubhamprakash681.config_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CombinedConfig {
    private AppConfig appConfig;
    private BuildInfo buildInfo;
}
