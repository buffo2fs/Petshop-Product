package com.lucas.petshop.repository;

import com.lucas.petshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByDeletedProductFalse();

    Product findByIdAndDeletedProductFalse(Long id);

}
