package in.shubhamprakash681.product.controllers;

import in.shubhamprakash681.product.dto.ProductIsActiveRequest;
import in.shubhamprakash681.product.dto.ProductRequest;
import in.shubhamprakash681.product.dto.ProductResponse;
import in.shubhamprakash681.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getProducts(@RequestParam(defaultValue = "1") int pageNo,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(defaultValue = "name") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortOrder) {
        Sort s;
        if (sortOrder.equals("DESC")) {
            s = Sort.by(sortBy).descending();
        }else {
            s = Sort.by(sortBy).ascending();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProducts(PageRequest.of(pageNo - 1, pageSize, s)));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest prodReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(prodReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse prodRes = productService.getProductById(id);

        if (prodRes == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(prodRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest prodReq) {
        ProductResponse updatedProd = productService.updateProduct(id, prodReq);

        if (updatedProd == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("/isActive/{id}")
    public ResponseEntity<ProductResponse> updateProductIsActive(@PathVariable Long id, @RequestBody ProductIsActiveRequest isActiveRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductIsActive(id, isActiveRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProducts(keyword));
    }

}
