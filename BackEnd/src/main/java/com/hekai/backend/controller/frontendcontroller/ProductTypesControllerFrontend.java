package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.ProductTypes;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/param")
public class ProductTypesControllerFrontend {

    @RequestMapping(value = "findallparams")
    public Result<List<ProductTypes>> findAllParams(){

        return null;
    }

}
