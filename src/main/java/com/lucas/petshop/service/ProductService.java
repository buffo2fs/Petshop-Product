package com.lucas.petshop.service;

import com.lucas.petshop.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    long createProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
