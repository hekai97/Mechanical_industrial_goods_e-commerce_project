package com.hekai.backend.controller.frontendcontriller;

import com.hekai.backend.entites.Product;
import com.hekai.backend.entites.reconstruction.Floor;
import com.hekai.backend.entites.reconstruction.PageBean;
import com.hekai.backend.entites.reconstruction.ProductDetail;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/product")
public class ProductControllerFrontend {
    @RequestMapping(value = "/findfloors")
    public Result<List<Floor>> findFloors(){
        return null;
    }

    @RequestMapping(value = "/findhotproducts")
    public Result<List<Product>> findHotProducts(@RequestBody int num){
        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<Product> getDetail(@RequestBody String productId){
        return null;
    }

    @RequestMapping(value = "/findproducts")
    public Result<PageBean<List<ProductDetail>>> findProducts(@RequestBody int productTypeId,
                                                              @RequestBody int partsId,
                                                              @RequestBody int pageNum,
                                                              @RequestBody int pageSize,
                                                              @RequestBody String name){
        return null;
    }


}
