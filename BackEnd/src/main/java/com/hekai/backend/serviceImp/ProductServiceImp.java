package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.Floor;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDesc;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDescAndHot;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.repository.ProductRepository;
import com.hekai.backend.repository.ProductTypesRepository;
import com.hekai.backend.service.ProductService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Result<List<Product>> findHotProducts(Integer num) {
        List<Product> hotProducts=productRepository.findAllByIsHot(ConstUtil.HotStatus.HOT_STATUS);
        return Result.createRespBySuccess(hotProducts.subList(0,num> hotProducts.size()? hotProducts.size() : num));
    }

    @Override
    public Result<Product> getDetailByProductId(Integer productId) {
        if(productId==null){
            return Result.createByErrorMessage("商品编号不能为空。");
        }
        Product product=productRepository.findProductById(productId);
        if(product==null){
            return Result.createByErrorMessage("商品不存在。");
        }
        if(product.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE){
            return Result.createByErrorMessage("商品已下架。");
        }
        return Result.createRespBySuccess(product);
    }

    @Override
    public Result<PageBean<List<ProductWithDescAndHot>>> findProducts(Integer productTypeId, Integer partsId, int pageNum, int pageSize, String name) {
        Pageable pageable= PageRequest.of(pageNum,pageSize);
        Page<Product> productPage=productRepository.findProductByProductIdAndPartsIdAndStatusAndNameLike(productTypeId,partsId,ConstUtil.ProductStatus.STATUS_ON_SALE,name,pageable);
        List<Product> productList=productPage.getContent();
        List<ProductWithDescAndHot> resultData=new ArrayList<>();
        for(Product p:productList){
            ProductTypes baseProductType=productTypesRepository.findProductTypesById(p.getProductId());
            ProductTypes detailProductType=productTypesRepository.findProductTypesById(p.getPartsId());
            ProductWithDescAndHot productWithDescAndHot=new ProductWithDescAndHot(p,baseProductType,detailProductType);
            resultData.add(productWithDescAndHot);
        }
        PageBean<List<ProductWithDescAndHot>> pageBean=new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage(productPage.getTotalPages());
        pageBean.setStartIndex((pageNum-1)*pageSize);
        pageBean.setTotalRecord((int)productPage.getTotalElements());
        pageBean.setData(resultData);
        if(productPage.hasPrevious()){
            pageBean.setPrePage(pageNum-1);
        }else{
            pageBean.setPrePage(pageNum);
        }

        if(productPage.hasNext()){
            pageBean.setNextPage(pageNum+1);
        }else{
            pageBean.setNextPage(pageNum);
        }
        return Result.createRespBySuccess(pageBean);
    }

    @Override
    public Result<List<Floor>> findFloors() {
        List<Product> productList=productRepository.findAll();
        productList.sort((o1, o2) -> {
            if(o1.getProductId()<o2.getProductId()){
                return -1;
            }else if(o1.getProductId()>o2.getProductId()){
                return 1;
            }
            return 0;
        });
        //这个数值决定了每个类型的返回多少个结果回去
        int number=8;
        //TODO 非常恶臭的功能，直接写死了

        return null;
    }
}
