package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.reConstruction.compositeEntities.Floor;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductDetail;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/product")
public class ProductControllerFrontend {
    @RequestMapping(value = "/findfloors")
    public Result<List<Floor>> findFloors(HttpSession httpSession){
        return null;
    }

    @RequestMapping(value = "/findhotproducts")
    public Result<List<Product>> findHotProducts(HttpSession httpSession,@RequestBody int num){
        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<Product> getDetail(HttpSession httpSession,@RequestBody String productId){
        return null;
    }

    @RequestMapping(value = "/findproducts")
    public Result<PageBean<List<ProductDetail>>> findProducts(HttpSession httpSession,
                                                              @RequestBody int productTypeId,
                                                              @RequestBody int partsId,
                                                              @RequestBody int pageNum,
                                                              @RequestBody int pageSize,
                                                              @RequestBody String name){
        return null;
    }


}
