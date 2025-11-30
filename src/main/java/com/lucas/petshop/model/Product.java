package com.lucas.petshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="animal_type")
    private String animalType;

    @Column(name="brand")
    private String brand;

    @Column(name="description")
    private String description;

    @Column(name="stock")
    private Integer stock;

    @Column(name="price")
    private Double price;

    @Column(name="size_weight")
    private Double sizeWeight;

    public Product() {

    }

    public Product(String name, String type, String animalType, String brand, String description, Integer stock, Double price, Double sizeWeight) {
        this.name = name;
        this.type = type;
        this.animalType = animalType;
        this.brand = brand;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.sizeWeight = sizeWeight;
    }

    public Long getId() {
        return this.id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getAnimalType(){
        return this.animalType;
    }

    public void setAnimalType(String animalType){
        this.animalType = animalType;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getStock(){
        return this.stock;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }

    public Double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Double getSizeWeight(){
        return this.sizeWeight;
    }

    public void setSizeWeight(Double sizeWeight){
        this.sizeWeight = sizeWeight;
    }

}



