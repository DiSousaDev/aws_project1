package br.dev.diego.aws_project1.entities.dtos;

import br.dev.diego.aws_project1.entities.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private String model;
    private String code;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String model, String code, double price) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.code = code;
        this.price = price;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        model = entity.getModel();
        code = entity.getCode();
        price = entity.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }
}
