package com.zagora17.secondhandclothes.config;

import com.zagora17.secondhandclothes.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] unsupportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH};

        //disable following http methods: POST, PUT, DELETE, PATCH
        disableHttpMethods(Product.class, config, unsupportedActions);
        disableHttpMethods(ProductCategory.class, config, unsupportedActions);
        disableHttpMethods(SuperCategory.class, config, unsupportedActions);
        disableHttpMethods(Province.class, config, unsupportedActions);
        disableHttpMethods(Place.class, config, unsupportedActions);


        // run exposeIds to make Ids available in the JSON response directly and simply
        this.exposeIds(config);

        //configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins("http://localhost:4200");
    }

    private <T> void disableHttpMethods(Class<T> theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        // get a Set of entity types from entityManager
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        // set up an array list containing java classes of all entity types.
        List<Class<?>> entityClasses = new ArrayList<>();

        // populate entityClasses list with the java types of every entityType
        entityTypes.forEach(entityType -> entityClasses.add(entityType.getJavaType()));

        //expose the ids for the array of classes

        Class<?>[] domainTypes = entityClasses.toArray(new Class<?>[0]);
        config.exposeIdsFor(domainTypes);

    }

}
