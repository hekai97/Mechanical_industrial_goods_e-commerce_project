package com.hekai.backend.controller;

import com.hekai.backend.entites.Product;
import com.hekai.backend.entites.reconstruction.PageBean;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "mgr/product")
public class ProductController {

    @RequestMapping(value = "pic_upload")
    public boolean picUpload(@RequestBody MultipartFile file){

        return true;
    }

    @RequestMapping(value = "productlist")
    public Result<List<Product>> productList(@Nullable @RequestBody int id){
        return null;
    }

    @RequestMapping(value = "upload")
    public boolean upload(@RequestBody MultipartFile file){
        return true;
    }

    @RequestMapping(value = "searchproducts")
    public Result<PageBean<List<Product>>> searchProducts(@RequestBody int pageNum,@RequestBody int pageSize,@RequestBody int id,@RequestBody String name){

        return null;
    }

    @RequestMapping(value = "getdetail")
    public Result<Product> getDetail(@RequestBody int productId){
        return null;
    }

    @RequestMapping(value = "setstatus")
    public Result<Product> setStatus(@RequestBody int productId,@RequestBody int status,@RequestBody int hot){
        return null;
    }

    @RequestMapping(value = "saveproduct")
    public Result<Product> saveProduct(@RequestBody String name,
                                       @RequestBody int productId,
                                       @RequestBody int partsId,
                                       @RequestBody String detail,
                                       @RequestBody String sepcParam,
                                       @RequestBody BigDecimal price,
                                       @RequestBody int stock,
                                       @RequestBody String subImages){
        return null;
    }

}
