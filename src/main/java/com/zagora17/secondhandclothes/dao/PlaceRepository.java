package com.zagora17.secondhandclothes.dao;

import com.zagora17.secondhandclothes.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "places", path = "places")
//@CrossOrigin("http://localhost:4200")
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Set<Place> findByProvinceIdOrderByNameAsc(@Param("id") Integer id);

    Set<Place> findByProvinceIdAndNameStartingWithOrderByNameAsc(@Param("id") Integer id, @Param("name") String name);
}
