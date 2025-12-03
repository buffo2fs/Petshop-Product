package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.mapper.ProductMapper;
import com.lucas.petshop.repository.ProductRepository;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL PRODUCTS] - Successfully", startTime);

        return productRepository.findAll()
                .stream()
                .filter(product -> !Boolean.TRUE.equals(product.getDeletedProduct()))
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        var startTime = System.currentTimeMillis();

        Product product = getProductIfExist(id);

        if (Boolean.TRUE.equals(product.getDeletedProduct())) {
            throw new RuntimeException("PRODUCT IS DELETED");
        }

        Timer.measure("[GET PRODUCT BY ID] - Successfully", startTime);
        return ProductMapper.toDTO(product);
    }

    @Override
    @Transactional
    public long createProduct(ProductRequestDTO dto) {
        var startTime = System.currentTimeMillis();
        Product products = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(products);

        Timer.measure("[CREATE PRODUCT] - Successfully", startTime);
        return saved.getId();
    }

    @Override
    @Transactional
    public void updateProduct(Long id, ProductRequestDTO dto) {
        var startTime = System.currentTimeMillis();

        Product existing = getProductIfExist(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())) {
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        existing.setName(dto.name());
        existing.setType(dto.type());
        existing.setAnimalType(dto.animalType());
        existing.setBrand(dto.brand());
        existing.setDescription(dto.description());
        existing.setStock(dto.stock());
        existing.setPrice(dto.price());
        existing.setSizeWeight(dto.sizeWeight());

        productRepository.save(existing);

        Timer.measure("[UPDATE PRODUCT] - Successfully", startTime);
    }

    @Override
    public void deleteProduct(Long id) {
        var startTime = System.currentTimeMillis();
        Product existing = getProductIfExist(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())) {
            throw new RuntimeException("PRODUCT ALREADY DELETED");
        }

        existing.setDeletedProduct(true);
        existing.setLastUpdate(java.time.LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[DELETE PRODUCT] - Successfully", startTime);
    }

    @Override
    @Transactional
    public ProductResponseDTO partialUpdateProduct(Long id, ProductUpdateDTO dto) {
        var startTime = System.currentTimeMillis();

        Product existing = getProductIfExist(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())) {
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        if (dto.name() != null) existing.setName(dto.name());
        if (dto.type() != null) existing.setType(dto.type());
        if (dto.animalType() != null) existing.setAnimalType(dto.animalType());
        if (dto.brand() != null) existing.setBrand((dto.brand()));
        if (dto.description() != null) existing.setDescription(dto.description());
        if (dto.stock() != null) existing.setStock(dto.stock());
        if (dto.price() != null) existing.setPrice(dto.price());
        if (dto.sizeWeight() != null) existing.setSizeWeight(dto.sizeWeight());

        existing.setLastUpdate(java.time.LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[PATCH PRODUCT] - Successfully", startTime);

        return ProductMapper.toDTO(existing);

    }

    private Product getProductIfExist(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;

    }
}



