package com.lucas.petshop.service;

import com.lucas.petshop.repository.ProductRepository;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        var startTime = System.currentTimeMillis();

        List<Product> productList = productRepository.findAll();

        Timer.measure("[GET ALL PRODUCTS] - Successfully", startTime);
        return productList;


    }

    @Override
    public Product getProductById(Long id) {
        var startTime = System.currentTimeMillis();

        Product products = productRepository.getById(id);

        Timer.measure("[GET PRODUCT BY ID] - Successfully", startTime);
        return products;
    }

    @Override
    @Transactional
    public long createProduct(Product product) {
        var startTime = System.currentTimeMillis();

        Product newProduct = productRepository.save(product);

        Timer.measure("[CREATE PRODUCT] - Successfully", startTime);
        return newProduct.getId();
    }

    @Override
    @Transactional
    public void updateProduct(Long id, Product product) {
        var startTime = System.currentTimeMillis();

        productRepository.save(product);

        Timer.measure("[UPDATE PRODUCT] - Successfully", startTime);
    }

    @Override
    public void deleteProduct(Long id) {
        var startTime = System.currentTimeMillis();

        productRepository.deleteById(id);

        Timer.measure("[DELETE PRODUCT] - Successfully", startTime);
    }

    private Product getProductIfExist(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }
}
