package in.shubhamprakash681.s13_configmanagement.controllers;

import in.shubhamprakash681.s13_configmanagement.models.AppConfig;
import in.shubhamprakash681.s13_configmanagement.models.BuildInfo;
import in.shubhamprakash681.s13_configmanagement.models.CombinedConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class BuildInfoController {
    @Value("${build.id}")
    private String buildId;
    @Value("${build.version}")
    private String buildVersion;
    @Value("${build.name}")
    private String buildName;
    @Value("${OS}")
    private String osName;
    @Value("${USERPROFILE}")
    private String osUserName;
    @Value("${JAVA_HOME}")
    private String javaHome;

    private final AppConfig appConfig;

    @GetMapping("/build-info")
    public ResponseEntity<String> GetBuildInfo(){
        BuildInfo buildInfo = new BuildInfo(buildId, buildVersion, buildName, osName, osUserName, javaHome);

        CombinedConfig combinedConfig = new CombinedConfig(appConfig, buildInfo);

        return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(combinedConfig));
    }
}
