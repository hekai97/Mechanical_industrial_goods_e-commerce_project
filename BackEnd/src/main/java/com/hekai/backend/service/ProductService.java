package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.Floor;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductDetail;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDesc;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDescAndHot;
import com.hekai.backend.entites.sourceEntites.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface ProductService {
    Result<List<ProductWithDesc>> productList(@Nullable Integer id);

    Result<PageBean<List<ProductWithDescAndHot>>> searchProducts(Integer pageNum, Integer pageSize, Integer id, String name);

    Result<List<Product>> findHotProducts(Integer num);

    Result<Product> getDetailByProductId(Integer productId);

    Result<PageBean<List<ProductWithDescAndHot>>> findProducts(Integer productTypeId, Integer partsId, int pageNum, int pageSize, String name);

    Result<Floor> findFloors();
}
