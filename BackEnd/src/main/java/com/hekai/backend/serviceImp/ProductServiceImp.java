package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDesc;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDescAndHot;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.repository.ProductRepository;
import com.hekai.backend.repository.ProductTypesRepository;
import com.hekai.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypesRepository productTypesRepository;

    @Override
    public Result<List<ProductWithDesc>> productList(Integer id) {
        List<ProductWithDesc> temp=new ArrayList<>();
        if(id==null){
            List<Product> products=productRepository.findAll();
            for(Product p:products){
                ProductTypes baseType=productTypesRepository.findProductTypesById(p.getProductId());
                ProductTypes detailType=productTypesRepository.findProductTypesById(p.getPartsId());
                ProductWithDesc productWithDesc=new ProductWithDesc(p,baseType,detailType);
                temp.add(productWithDesc);
            }
        }else{
            Product product=productRepository.findProductById(id);
            ProductTypes baseType=productTypesRepository.findProductTypesById(product.getProductId());
            ProductTypes detailType=productTypesRepository.findProductTypesById(product.getPartsId());
            ProductWithDesc productWithDesc=new ProductWithDesc(product,baseType,detailType);
            temp.add(productWithDesc);
        }
        Result<List<ProductWithDesc>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(temp);
        return result;
    }

    @Override
    public Result<PageBean<List<ProductWithDescAndHot>>> searchProducts(Integer pageNum, Integer pageSize, Integer id, String name) {


        Result<PageBean<List<ProductWithDescAndHot>>> result=new Result<>();
        return null;
    }
}
