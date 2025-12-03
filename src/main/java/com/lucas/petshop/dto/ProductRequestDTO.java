package com.lucas.petshop.dto;

import jakarta.validation.constraints.*;

public record ProductRequestDTO(

        @NotBlank(message = "PRODUCT NAME IS REQUIRED")
        @Size(min = 3, max = 80, message = "PRODUCT NAME MUST CONTAIN BETWEEN 3 - 80 CHARACTERS")
        String name,

        @NotBlank(message = "PRODUCT TYPE IS REQUIRED")
        String type,

        @NotBlank(message = "PRODUCT ANIMAL TYPE IS REQUIRED")
        String animalType,

        @NotBlank(message = "PRODUCT BRAND IS REQUIRED")
        String brand,

        @NotBlank(message = "PRODUCT DESCRIPTION IS REQUIRED")
        @Size(min = 10, max = 255, message = "PRODUCT DESCRIPTION MUST CONTAIN BETWEEN 10 - 255 CHARACTERS")
        String description,

        @NotNull(message = "PRODUCT STOCK IS REQUIRED")
        @Min(value = 0, message = "PRODUCT STOCK CANNOT BE NEGATIVE")
        Integer stock,

        @NotNull(message = "PRODUCT PRICE IS REQUIRED")
        @Positive(message = "PRODUCT PRICE MUST BE HIGHER THAN 0 (ZERO)")
        Double price,

        @NotNull(message = "PRODUCT SIZE AND WEIGHT IS REQUIRED")
        @Positive(message = "PRODUCT SIZE AND WEIGHT MUST BE HIGHER THAN 0 (ZERO)")
        Double sizeWeight
) {}


