package com.hekai.backend.entites.reConstruction.compositeEntities;

import com.hekai.backend.entites.sourceEntites.Cart;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.User;

import java.math.BigDecimal;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class CartWithProduct {

    private int id;
    private int userId;
    private int productId;
    private String name;
    private int quantity;
    private BigDecimal price;
    private int status;
    private BigDecimal totalPrice;
    private int stock;
    private String iconUrl;
    private int checked;

    public CartWithProduct(Cart cart, User user, Product product,int check){
        this.id=cart.getId();
        this.userId=user.getId();
        this.productId= cart.getProductId();
        this.name=product.getName();
        this.quantity=cart.getQuantity();
        this.price=product.getPrice();
        this.status=product.getStatus();
        this.totalPrice=BigDecimal.valueOf(price.doubleValue()*quantity);
        this.stock=product.getStock();
        this.iconUrl= product.getIconUrl();
        this.checked=check;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int isChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

}
