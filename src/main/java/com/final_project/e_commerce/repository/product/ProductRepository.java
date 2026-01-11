package com.final_project.e_commerce.repository.product;

import com.final_project.e_commerce.data.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query(
            nativeQuery = true,
            value = "select * from product " +
                    "where (?1 is null or name like concat('%', ?1, '%')) " +
                    "and (?2 is null or product_category = ?2) ",
            countQuery = "select * from product " +
                    "where (?1 is null or name like concat('%', ?1, '%')) " +
                    "and (?2 is null or product_category = ?2) "
    )
    Page<ProductEntity> getAllProductZh(String keyword, String category, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select * from product " +
                    "where (?1 is null or name_en like concat('%', ?1, '%')) " +
                    "and (?2 is null or product_category = ?2) ",
            countQuery = "select * from product " +
                    "where (?1 is null or name_en like concat('%', ?1, '%')) " +
                    "and (?2 is null or product_category = ?2) "
    )
    Page<ProductEntity> getAllProductEn(String keyword, String category, Pageable  pageable);


    @Query(
            nativeQuery = true,
            value = "select * from product where pid = ?1"
    )
    Optional<ProductEntity> getProductById(String pid);
}
