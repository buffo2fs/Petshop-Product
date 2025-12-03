package com.lucas.petshop.controller;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.exception.ProductNotFoundException;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired // SpringBoot faz o trabalho
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable(value = "productId") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequestDTO dto){
       Long id = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable(value = "productId") Long id, @Valid @RequestBody ProductRequestDTO dto){
        productService.updateProduct(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> partialUpdate(
            @PathVariable(value = "productId") Long id, @Valid @RequestBody ProductUpdateDTO dto) {
        ProductResponseDTO updated = productService.partialUpdateProduct(id, dto);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "productId") Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
