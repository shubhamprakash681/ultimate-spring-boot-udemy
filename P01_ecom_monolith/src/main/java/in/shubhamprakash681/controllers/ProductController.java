package in.shubhamprakash681.controllers;

import in.shubhamprakash681.dto.ProductIsActiveRequest;
import in.shubhamprakash681.dto.ProductRequest;
import in.shubhamprakash681.dto.ProductResponse;
import in.shubhamprakash681.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest prodReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(prodReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest prodReq) {
        ProductResponse updatedProd = productService.updateProduct(id, prodReq);

        if (updatedProd == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

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
