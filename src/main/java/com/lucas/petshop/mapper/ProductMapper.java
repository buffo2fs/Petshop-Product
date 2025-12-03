package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.model.Product;

public class ProductMapper {

    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                product.getType(),
                product.getAnimalType(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getSizeWeight()
        );
    }

    public static Product toEntity(ProductRequestDTO dto){
        Product product = new Product();
        product.setName(dto.name());
        product.setType(dto.type());
        product.setAnimalType(dto.animalType());
        product.setBrand(dto.brand());
        product.setDescription(dto.description());
        product.setStock(dto.stock());
        product.setPrice(dto.price());
        product.setSizeWeight(dto.sizeWeight());

        return product;
    }
}

