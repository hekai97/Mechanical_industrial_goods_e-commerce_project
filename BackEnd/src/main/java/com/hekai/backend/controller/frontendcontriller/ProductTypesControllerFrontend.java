package com.hekai.backend.controller.frontendcontriller;

import com.hekai.backend.entites.ProductTypes;
import com.hekai.backend.entites.reconstruction.Result;
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
