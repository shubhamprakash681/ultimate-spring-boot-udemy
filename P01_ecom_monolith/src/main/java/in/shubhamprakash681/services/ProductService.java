package in.shubhamprakash681.services;

import in.shubhamprakash681.dto.ProductIsActiveRequest;
import in.shubhamprakash681.dto.ProductRequest;
import in.shubhamprakash681.dto.ProductResponse;
import in.shubhamprakash681.models.Product;
import in.shubhamprakash681.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponse> getProducts() {
        return productRepository.findByIsActiveTrue().stream().map(this::mapProductToProductResponse).toList();
    }

    public ProductResponse createProduct(ProductRequest prodReq) {
        Product createdProd = productRepository.save(mapProductRequestToProduct(prodReq));

        return mapProductToProductResponse(createdProd);
    }

    public ProductResponse updateProduct(long id, ProductRequest prodReq) {
        return productRepository.findById(id).map(existingProd -> {
            existingProd.setName(prodReq.getName());
            existingProd.setDescription(prodReq.getDescription());
            existingProd.setPrice(prodReq.getPrice());
            existingProd.setCategory(prodReq.getCategory());
            existingProd.setImageUrl(prodReq.getImageUrl());
            existingProd.setStockQuantity(prodReq.getStockQuantity());
            if (prodReq.getIsActive() != null) existingProd.setIsActive(prodReq.getIsActive());

            productRepository.save(existingProd);

            return productRepository.findById(id).map(this::mapProductToProductResponse).orElse(null);
        }).orElse(null);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public ProductResponse updateProductIsActive(Long id, ProductIsActiveRequest isActiveRequest) {
        return productRepository.findById(id).map(existingProd -> {
            existingProd.setIsActive(isActiveRequest.getIsActive());

            productRepository.save(existingProd);
            return productRepository.findById(id).map(this::mapProductToProductResponse).orElse(null);
        }).orElse(null);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream().map(this::mapProductToProductResponse).toList();
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse prodRes = new ProductResponse();
        prodRes.setId(String.valueOf(product.getId()));
        prodRes.setName(product.getName());
        prodRes.setDescription(product.getDescription());
        prodRes.setCategory(product.getCategory());
        prodRes.setPrice(product.getPrice());
        prodRes.setStockQuantity(product.getStockQuantity());
        prodRes.setImageUrl(product.getImageUrl());
        prodRes.setIsActive(product.getIsActive());

        return prodRes;
    }

    private Product mapProductRequestToProduct(ProductRequest prodReq) {
        Product product = new Product();

        if (prodReq.getId() != null) product.setId(Long.valueOf(prodReq.getId()));
        product.setName(prodReq.getName());
        product.setDescription(prodReq.getDescription());
        product.setCategory(prodReq.getCategory());
        product.setPrice(prodReq.getPrice());
        product.setStockQuantity(prodReq.getStockQuantity());
        product.setImageUrl(prodReq.getImageUrl());
        if (prodReq.getIsActive() != null) product.setIsActive(prodReq.getIsActive());

        return product;
    }
}
