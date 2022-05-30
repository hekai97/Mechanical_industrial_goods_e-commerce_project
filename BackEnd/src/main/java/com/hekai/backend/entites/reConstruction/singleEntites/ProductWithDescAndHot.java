package com.hekai.backend.entites.reConstruction.singleEntites;

import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.ProductTypes;

/**
 * @author: hekai
 * @Date: 2022/5/30
 */
public class ProductWithDescAndHot extends ProductWithDesc{
    private String iconUrl;
    private int hot;
    private String hotStatus;
    private static final int IsHot=1;
    private static final int NotHot=2;
    public ProductWithDescAndHot(Product product, ProductTypes basicType, ProductTypes detailedType) {
        super(product, basicType, detailedType);
        this.iconUrl=product.getIconUrl();
        this.hot=product.getIsHot();
        switch (product.getIsHot()){
            case IsHot -> this.hotStatus="热销";
            case NotHot -> this.hotStatus="一般";
        }
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
