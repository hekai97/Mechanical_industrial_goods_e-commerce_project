package com.hekai.backend.controller;

import com.hekai.backend.entites.Order;
import com.hekai.backend.entites.ProductTypes;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "mgr/param")
public class ProductTypesController {

    @RequestMapping(value = "delparam")
    public Result<ProductTypes> delParam(@RequestBody int id){
        return null;
    }

    /**
     * 获取带路径的参数信息接口
     * 未实现
     * */

    @RequestMapping(value = "findpartstype")
    public Result<ProductTypes> findPartStyle(@RequestBody int productTypeId){

        return null;
    }

    @RequestMapping(value = "findptype")
    public Result<List<ProductTypes>> findPtype(){

        return null;
    }

    @RequestMapping(value = "findchildren")
    public Result<List<ProductTypes>> findChildren(@RequestBody int id){
        return null;
    }

    @RequestMapping(value = "updateparam")
    public Result<ProductTypes> updateParam(@RequestBody String name,@RequestBody int id){
        return null;
    }

    @RequestMapping(value = "saveparam")
    public Result<ProductTypes> saveParam(@RequestBody String name,@RequestBody int parent_id){
        return null;
    }

}
