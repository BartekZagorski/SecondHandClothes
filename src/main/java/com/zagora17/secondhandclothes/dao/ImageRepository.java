package com.zagora17.secondhandclothes.dao;

import com.zagora17.secondhandclothes.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CrossOrigin("http://localhost:4200")
public interface ImageRepository extends JpaRepository<Image, Long> {
    public List<Image> findByProductId(Long id);
}
