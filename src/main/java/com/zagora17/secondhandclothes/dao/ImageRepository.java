package com.zagora17.secondhandclothes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.awt.*;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
@CrossOrigin("http://localhost:4200")
public interface ImageRepository extends JpaRepository<com.zagora17.secondhandclothes.entity.Image, Long> {
}
