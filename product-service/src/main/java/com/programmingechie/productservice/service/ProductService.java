package com.programmingechie.productservice.service;

import com.programmingechie.productservice.dto.ProductRequestDto;
import com.programmingechie.productservice.dto.ProductResponseDto;
import com.programmingechie.productservice.model.Product;
import com.programmingechie.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} created and saved  in DB." , product.getId());
    }

    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductRespons).toList();
    }

    private ProductResponseDto mapToProductRespons(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
