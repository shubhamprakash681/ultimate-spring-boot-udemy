package in.shubhamprakash681.s13_configmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildInfo {
    private String buildId;
    private String buildVersion;
    private String buildName;
    private String osName;
    private String osUserName;
    private String javaHome;
}
