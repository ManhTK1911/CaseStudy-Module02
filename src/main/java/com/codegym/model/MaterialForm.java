package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.sql.Date;

public class MaterialForm {
    private Long id;
    @Max(50)
    private String code, name;
    private Date importDate;
    private String description;
    private Long price;
    private String quantity;
    private MultipartFile image;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public MaterialForm() {
    }

    public MaterialForm(String code, String name, Date importDate, String description, Long price, String quantity, MultipartFile image) {
        this.code = code;
        this.name = name;
        this.importDate = importDate;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
