package in.shubhamprakash681.consumer.usinghttpinterface;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/http-interface/web-client")
@RequiredArgsConstructor
public class HttpInterfaceController {
    public final HttpInterfaceService httpInterfaceService;

    @GetMapping("/instance")
    public  String getInstance() {
        return httpInterfaceService.getInstanceInfo();
    }
}
