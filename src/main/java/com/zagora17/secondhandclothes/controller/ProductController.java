package com.zagora17.secondhandclothes.controller;

import com.zagora17.secondhandclothes.dto.ProductDTO;
import com.zagora17.secondhandclothes.dto.Purchase;
import com.zagora17.secondhandclothes.dto.PurchaseResponse;
import com.zagora17.secondhandclothes.entity.Product;
import com.zagora17.secondhandclothes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@CrossOrigin("https://localhost:4200")
@RestController
@RequestMapping("/api/products")
class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    ResponseEntity<Product> placeProduct(@RequestPart ProductDTO product, @RequestParam List<MultipartFile> images ) {
        return ResponseEntity.ok(this.productService.placeProduct(product, images));
    }

    @DeleteMapping
    ResponseEntity<?> deleteProduct(@RequestParam String id) {
        this.productService.deleteProduct(Long.valueOf(id));
        return ResponseEntity.accepted().build();
    }

}
