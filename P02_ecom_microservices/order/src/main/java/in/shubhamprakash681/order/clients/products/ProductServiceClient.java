package in.shubhamprakash681.order.clients.products;

import in.shubhamprakash681.order.dto.product.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(accept = "application/json", contentType = "application/json")
public interface ProductServiceClient {
    @GetExchange("/api/products/{id}")
    ProductResponse getProductById(@PathVariable("id") String id);
}