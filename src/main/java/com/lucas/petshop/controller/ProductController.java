package com.lucas.petshop.controller;

import com.lucas.petshop.exception.ProductNotFoundException;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        List<Product> productList = productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable(value = "productId") Long id){
        Product product = productRepository.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product newProduct){
        productRepository.save(newProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newUpdate, @PathVariable(value = "productId") Long id){
        Product getProduct = productRepository.getById(id);

        if(getProduct == null)
            throw new ProductNotFoundException("Produto não encontrado");

        newUpdate.setId(id);
        productRepository.save(newUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "productId") Long id){
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
