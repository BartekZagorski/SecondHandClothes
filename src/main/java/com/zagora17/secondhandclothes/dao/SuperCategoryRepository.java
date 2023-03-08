package com.zagora17.secondhandclothes.dao;

import com.zagora17.secondhandclothes.entity.SuperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "superCategories", path = "super-categories")
@CrossOrigin("http://localhost:4200")
public interface SuperCategoryRepository extends JpaRepository<SuperCategory, Long> {
}
