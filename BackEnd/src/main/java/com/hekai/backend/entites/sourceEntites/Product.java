package com.hekai.backend.entites.sourceEntites;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Entity
@Table(name = "product", schema = "mydatabase")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "parts_id")
    private int partsId;
    @Basic
    @Column(name = "icon_url")
    private String iconUrl;
    @Basic
    @Column(name = "sub_images")
    private String subImages;
    @Basic
    @Column(name = "detail")
    private String detail;
    @Basic
    @Column(name = "spec_param")
    private String specParam;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "stock")
    private int stock;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "is_hot")
    private int isHot;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPartsId() {
        return partsId;
    }

    public void setPartsId(int partsId) {
        this.partsId = partsId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpecParam() {
        return specParam;
    }

    public void setSpecParam(String specParam) {
        this.specParam = specParam;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id == that.id && productId == that.productId && partsId == that.partsId && price == that.price && stock == that.stock && status == that.status && isHot == that.isHot && Objects.equals(name, that.name) && Objects.equals(iconUrl, that.iconUrl) && Objects.equals(subImages, that.subImages) && Objects.equals(detail, that.detail) && Objects.equals(specParam, that.specParam) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productId, partsId, iconUrl, subImages, detail, specParam, price, stock, status, isHot, created, updated);
    }
}
