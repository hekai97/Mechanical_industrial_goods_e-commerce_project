package com.hekai.backend.entites.reconstruction;

import com.hekai.backend.entites.Product;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class Floor {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
