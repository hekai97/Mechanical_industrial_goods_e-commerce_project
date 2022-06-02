package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.ProductTypes;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface ProductTypesService {
    Result<ProductTypes> delParam(int id);

    Result<List<ProductTypes>> findPartStyle(int productTypeId);

    Result<List<ProductTypes>> findPType();

    Result<List<ProductTypes>> findChildren(int id);

    Result<ProductTypes> updateParam(String name, int id);

    Result<ProductTypes> saveParam(String name, int parent_id);

    Result<List<ProductTypes>> findAllParams();
}
