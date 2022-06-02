package com.hekai.backend.entites.reConstruction.singleEntites;

import java.math.BigDecimal;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class ProductDetail {
    private int id;
    private String name;
    private BigDecimal price;
    private int status;
    private String statusDesc;
    private String productCategory;
    private String partsCategory;
    private String iconUrl;
    private int hot;
    private String hotStatus;


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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getHotStatus() {
        return hotStatus;
    }

    public void setHotStatus(String hotStatus) {
        this.hotStatus = hotStatus;
    }
}
