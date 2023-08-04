package com.zagora17.secondhandclothes.service;

import com.zagora17.secondhandclothes.dao.ProductCategoryRepository;
import com.zagora17.secondhandclothes.dao.ProductRepository;
import com.zagora17.secondhandclothes.dto.ProductDTO;
import com.zagora17.secondhandclothes.entity.Image;
import com.zagora17.secondhandclothes.entity.Product;
import com.zagora17.secondhandclothes.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final String FOLDER_PATH="/work/second-hand-clothes-app/second-hand-clothes-frontend/second-hand-clothes" +
            "/src/";

    private ProductRepository repository;
    private ProductCategoryRepository categoryRepository;

    private ImageService imageService;
    @Autowired
    ProductService(ProductRepository repository, ProductCategoryRepository categoryRepository, ImageService imageService) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }
    @Transactional
    public Product placeProduct(List<MultipartFile> files, ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setUnitPrice((productDTO.getUnitPrice()));
        product.setImageUrl(productDTO.getImageUrl());

        String categoryName = productDTO.getCategory();
        System.out.println(categoryName);
        ProductCategory category = this.categoryRepository.findByName(categoryName);

        product.setCategory(category);

        if (files != null) {

        var images = files.stream().map(file-> {
            try {
                this.imageService.createFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            var image = Image.builder()
                            .product(product)
                            .url("assets/"+file.getOriginalFilename())
                            .build();
            System.out.println(image);
            return image;
        }).toList();

        product.setImages(images);
        product.setImageUrl(images.get(0).getUrl());
        }

        this.repository.save(product);

        return product;
    }
}
