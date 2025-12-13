package in.shubhamprakash681.s13_configmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CombinedConfig {
    private AppConfig appConfig;
    private BuildInfo buildInfo;
}
