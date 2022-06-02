package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.repository.ProductRepository;
import com.hekai.backend.repository.ProductTypesRepository;
import com.hekai.backend.service.ProductTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class ProductTypesServiceImp implements ProductTypesService {

    @Autowired
    private ProductTypesRepository productTypesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Result<ProductTypes> delParam(int id) {
        ProductTypes productTypes=productTypesRepository.findProductTypesById(id);
        List<ProductTypes> childrenProductTypes=productTypesRepository.findAllByParentId(productTypes.getParentId());
        List<Product> products;
        if(productTypes.getParentId()==0){
            products=productRepository.findAllByProductId(0);
        }else{
            products=productRepository.findAllByPartsId(productTypes.getParentId());
        }
        Result<ProductTypes> result=new Result<>();
        if(!childrenProductTypes.isEmpty()){
            result.setStatus(Result.Error);
            result.setMsg("请先删除子类型！");
        }else if(!products.isEmpty()){
            result.setStatus(Result.Error);
            result.setMsg("不能删除有商品的类型！");
        }else{
            productTypesRepository.delete(productTypes);
            result.setStatus(Result.Normal);
        }
        return result;
    }

    @Override
    public Result<List<ProductTypes>> findPartStyle(int productTypeId) {
        //这里就不新定义对象了，直接返回productType中的id和name就行
        //返回的应该是level对应的数据
        List<ProductTypes> productTypes=productTypesRepository.findProductTypesByLevel(productTypeId);
        Result<List<ProductTypes>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(productTypes);
        return result;
    }

    @Override
    public Result<List<ProductTypes>> findPType() {
        List<ProductTypes> topProduct=productTypesRepository.findAllByParentId(0);
        Result<List<ProductTypes>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(topProduct);
        return result;
    }

    @Override
    public Result<List<ProductTypes>> findChildren(int id) {
        List<ProductTypes> children=productTypesRepository.findAllByParentId(id);
        Result<List<ProductTypes>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(children);
        return result;
    }

    @Override
    public Result<ProductTypes> updateParam(String name, int id) {
        ProductTypes productTypes=productTypesRepository.findProductTypesById(id);
        productTypes.setName(name);
        productTypes.setUpdate(new Timestamp(System.currentTimeMillis()));
        ProductTypes res=productTypesRepository.save(productTypes);
        Result<ProductTypes> result=new Result<>();
        if(res.getName().equals(name)){
            result.setStatus(Result.Normal);
            result.setMsg("产品参数修改成功！");
        }else{
            result.setStatus(Result.Error);
            result.setMsg("产品参数修改失败！");
        }
        return result;
    }

    @Override
    public Result<ProductTypes> saveParam(String name, int parent_id) {
        ProductTypes productTypes=new ProductTypes();
        productTypes.setName(name);
        productTypes.setParentId(parent_id);
        productTypes.setCreated(new Timestamp(System.currentTimeMillis()));
        Result<ProductTypes> result=new Result<>();
        ProductTypes res=productTypesRepository.save(productTypes);
        if(res.getName().equals(name)){
            result.setStatus(Result.Normal);
            result.setMsg("产品参数修改成功！");
        }else{
            result.setStatus(Result.Error);
            result.setMsg("产品参数修改失败！");
        }
        return result;
    }

    @Override
    public Result<List<ProductTypes>> findAllParams() {
        List<ProductTypes> parentList=productTypesRepository.findAllByParentId(0);
        for (ProductTypes productTypes:parentList){
            //递归获取产品数据
            findChildrenToParent(productTypes);
        }
        return Result.createRespBySuccess(parentList);
    }

    private void findChildrenToParent(ProductTypes productTypes) {
        List<ProductTypes> parentList=productTypesRepository.findAllByParentId(productTypes.getId());
        for(ProductTypes p:parentList){
            findChildrenToParent(p);
        }
    }

}
