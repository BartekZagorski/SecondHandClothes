package com.zagora17.secondhandclothes.dao;

import com.zagora17.secondhandclothes.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "productCategories", path = "product-categories")
//@CrossOrigin("http://localhost:4200")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByName(String categoryName);

    List<ProductCategory> findBySuperCategoryName(@Param("name") String superCategoryName);

}
