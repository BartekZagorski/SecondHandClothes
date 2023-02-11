package com.zagora17.secondhandclothes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_categories")
@Data
class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
