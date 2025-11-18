package com.final_project.e_commerce.repository.product;

import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {

    @Query(
            nativeQuery = true,
            value = "select * from product"
    )
    List<ProductEntity> getProduct();


    @Query(
            nativeQuery = true,
            value = "select * from product where pid = ?1"
    )
    Optional<ProductEntity> getProductById(String pid);
}
