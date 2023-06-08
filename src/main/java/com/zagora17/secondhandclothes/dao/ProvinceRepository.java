package com.zagora17.secondhandclothes.dao;

import com.zagora17.secondhandclothes.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "provinces", path = "provinces")
//@CrossOrigin("http://localhost:4200")
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
