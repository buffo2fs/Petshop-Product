package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.model.Product;
import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    long createProduct(ProductRequestDTO product);

    void updateProduct(Long id, ProductRequestDTO product);

    void deleteProduct(Long id);

    ProductResponseDTO partialUpdateProduct(Long id, ProductUpdateDTO product);
}