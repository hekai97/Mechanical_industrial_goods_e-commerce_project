package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.serviceImp.ProductTypesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "mgr/param")
public class ProductTypesControllerBackend {

    @Autowired
    private ProductTypesServiceImp productTypesServiceImp;

    @RequestMapping(value = "/delparam.do")
    public Result<ProductTypes> delParam(HttpSession httpSession, @RequestBody int id){
        return productTypesServiceImp.delParam(id);
    }

    /**
     * 获取带路径的参数信息接口
     * 未实现
     * */

    @RequestMapping(value = "/findpartstype.do")
    public Result<List<ProductTypes>> findPartStyle(HttpSession httpSession,@RequestBody int productTypeId){
        return productTypesServiceImp.findPartStyle(productTypeId);
    }

    @RequestMapping(value = "/findptype.do")
    public Result<List<ProductTypes>> findPtype(HttpSession httpSession){
        return productTypesServiceImp.findPType();
    }

    @RequestMapping(value = "/findchildren.do")
    public Result<List<ProductTypes>> findChildren(HttpSession httpSession,@RequestBody int id){
        return productTypesServiceImp.findChildren(id);
    }

    @RequestMapping(value = "/updateparam.do")
    public Result<ProductTypes> updateParam(HttpSession httpSession,@RequestBody String name,@RequestBody int id){
        return productTypesServiceImp.updateParam(name,id);
    }

    @RequestMapping(value = "/saveparam.do")
    public Result<ProductTypes> saveParam(HttpSession httpSession,@RequestBody String name,@RequestBody int parent_id){
        return productTypesServiceImp.saveParam(name,parent_id);
    }

}
