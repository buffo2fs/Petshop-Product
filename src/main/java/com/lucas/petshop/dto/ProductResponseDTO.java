package com.lucas.petshop.dto;

import java.time.LocalDateTime;

public record ProductResponseDTO(
        String name,
        String type,
        String animalType,
        String brand,
        String description,
        Double price,
        Double SizeWeight
) {}

