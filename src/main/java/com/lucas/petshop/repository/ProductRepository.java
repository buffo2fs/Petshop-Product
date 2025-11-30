package com.lucas.petshop.repository;

import com.lucas.petshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{


}
