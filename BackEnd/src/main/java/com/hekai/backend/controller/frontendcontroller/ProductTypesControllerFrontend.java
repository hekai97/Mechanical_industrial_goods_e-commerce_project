package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.service.ProductTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/param")
public class ProductTypesControllerFrontend {
    @Autowired
    private ProductTypesService productTypesService;

    @RequestMapping(value = "findallparams")
    public Result<List<ProductTypes>> findAllParams(HttpSession httpSession){
        return productTypesService.findAllParams();
    }

}
