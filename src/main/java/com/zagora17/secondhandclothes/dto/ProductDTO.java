package com.zagora17.secondhandclothes.dto;

import com.zagora17.secondhandclothes.entity.Image;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private String category;
}
