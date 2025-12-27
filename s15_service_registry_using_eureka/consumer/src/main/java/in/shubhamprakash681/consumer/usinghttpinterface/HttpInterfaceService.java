package in.shubhamprakash681.consumer.usinghttpinterface;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange()
public interface HttpInterfaceService {
    @GetExchange("/instance-info")
    String getInstanceInfo();
}
