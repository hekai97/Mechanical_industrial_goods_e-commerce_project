package com.hekai.backend.entites.reConstruction.singleEntites;

import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.ProductTypes;

import java.math.BigDecimal;

/**
 * @author: hekai
 * @Date: 2022/5/30
 */
public class ProductWithDesc {
    private int id;
    private String name;
    private BigDecimal price;
    private int status;
    private String statusDesc;
    private String productCategory;
    private String partsCategory;

    //待售，刚保存
    private static final int ForSale=1;
    //上架，在售
    private static final int OnSale=2;
    //下架，停售
    private static final int StopSale=3;

    public ProductWithDesc(Product product, ProductTypes basicType,ProductTypes detailedType){
        this.id= product.getId();
        this.name=product.getName();
        this.price=product.getPrice();
        this.status= product.getStatus();
        switch (product.getStatus()){
            case ForSale -> this.statusDesc="代售，刚保存";
            case OnSale -> this.statusDesc="上架，在售";
            case StopSale -> this.statusDesc="下架，代售";
        }
        this.productCategory= basicType.getName();
        this.partsCategory=detailedType.getName();
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getPartsCategory() {
        return partsCategory;
    }

    public void setPartsCategory(String partsCategory) {
        this.partsCategory = partsCategory;
    }
}
