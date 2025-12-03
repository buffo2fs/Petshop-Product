package com.lucas.petshop.dto;

import jakarta.validation.constraints.*;

public record ProductUpdateDTO(

        @Size(min = 3, max = 80, message = "PRODUCT NAME SHOULD HAVE BETWEEN 3 TO 80 CHARACTERS")
        String name,

        @Size(min = 2, max = 60, message = "PRODUCT TYPE SHOULD HAVE BETWEEN 2 TO 60 CHARACTERS")
        String type,

        @Size(min = 2, max = 30, message = "PRODUCT ANIMAL TYPE SHOULD HAVE BETWEEN 2 TO 30 CHARACTERS")
        String animalType,

        @Size(min = 2, max = 30, message = "PRODUCT BRAND SHOULD HAVE BETWEEN 2 TO 30 CHARACTERS")
        String brand,

        @Size(min = 10, max = 255, message = "PRODUCT DESCRIPTION SHOULD HAVE BETWEEN 10 - 255 CHARACTERS")
        String description,

        @Min(value = 0, message = "PRODUCT STOCK CANNOT BE NEGATIVE")
        Integer stock,

        @Positive(message = "PRODUCT PRICE SHOULD BE HIGHER THAN 0 (ZERO)")
        Double price,

        @Positive(message = "PRODUCT SIZE AND WEIGHT SHOULD BE HIGHER THAN 0 (ZERO)")
        Double sizeWeight


) {}
