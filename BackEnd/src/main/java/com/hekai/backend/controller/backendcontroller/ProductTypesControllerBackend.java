package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.serviceImp.ProductTypesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "mgr/param")
public class ProductTypesControllerBackend {

    @Autowired
    private ProductTypesServiceImp productTypesServiceImp;

    @RequestMapping(value = "delparam")
    public Result<ProductTypes> delParam(@RequestBody int id){
        return productTypesServiceImp.delParam(id);
    }

    /**
     * 获取带路径的参数信息接口
     * 未实现
     * */

    @RequestMapping(value = "findpartstype")
    public Result<List<ProductTypes>> findPartStyle(@RequestBody int productTypeId){
        return productTypesServiceImp.findPartStyle(productTypeId);
    }

    @RequestMapping(value = "findptype")
    public Result<List<ProductTypes>> findPtype(){
        return productTypesServiceImp.findPType();
    }

    @RequestMapping(value = "findchildren")
    public Result<List<ProductTypes>> findChildren(@RequestBody int id){
        return productTypesServiceImp.findChildren(id);
    }

    @RequestMapping(value = "updateparam")
    public Result<ProductTypes> updateParam(@RequestBody String name,@RequestBody int id){
        return productTypesServiceImp.updateParam(name,id);
    }

    @RequestMapping(value = "saveparam")
    public Result<ProductTypes> saveParam(@RequestBody String name,@RequestBody int parent_id){
        return productTypesServiceImp.saveParam(name,parent_id);
    }

}
