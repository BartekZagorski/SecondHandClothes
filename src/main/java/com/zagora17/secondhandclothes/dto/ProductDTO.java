package com.zagora17.secondhandclothes.dto;

import com.zagora17.secondhandclothes.entity.Image;
import com.zagora17.secondhandclothes.entity.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private String category;
    private Set<Image> images;
}
