package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDescAndHot;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.reConstruction.compositeEntities.Floor;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductDetail;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.service.ProductService;
import com.hekai.backend.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/product")
public class ProductControllerFrontend {

    @Autowired
    private ProductService productService;

    //PASS
    @RequestMapping(value = "/findfloors.do",method = RequestMethod.POST)
    public Result<Floor> findFloors(HttpSession httpSession){
        return productService.findFloors();
    }
    //PASS
    @RequestMapping(value = "/findhotproducts.do",method = RequestMethod.POST)
    public Result<List<Product>> findHotProducts(HttpSession httpSession,Integer num){
        return productService.findHotProducts(num);
    }
    //PASS
    @RequestMapping(value = "/getdetail.do",method = RequestMethod.POST)
    public Result<Product> getDetail(HttpSession httpSession,Integer productId){
        return productService.getDetailByProductId(productId);
    }


    //NOT-PASS
    @RequestMapping(value = "/findproducts.do",method = RequestMethod.POST)
    public Result<PageBean<List<ProductWithDescAndHot>>> findProducts(HttpSession httpSession,
                                                                      Integer productTypeId,
                                                                      Integer partsId,
                                                                      @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                                      @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                                                      @RequestBody String name){
        return productService.findProducts(productTypeId,partsId,pageNum,pageSize,name);
    }


}
