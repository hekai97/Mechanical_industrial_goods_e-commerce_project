package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDesc;
import com.hekai.backend.entites.reConstruction.singleEntites.ProductWithDescAndHot;
import com.hekai.backend.entites.sourceEntites.Product;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "mgr/product")
public class ProductControllerBackend {
    @Autowired
    private ProductServiceImp productServiceImp;
    //TODO 商品图片上传模块，这块暂时先不做
    @RequestMapping(value = "/pic_upload.do")
    public boolean picUpload(HttpSession httpSession, @RequestBody MultipartFile file){

        return true;
    }

    @RequestMapping(value = "/productlist.do")
    public Result<List<ProductWithDesc>> productList(HttpSession httpSession,@RequestBody @Nullable Integer id){
        return productServiceImp.productList(id);
    }

    //TODO 过段时间写
    @RequestMapping(value = "/upload.do")
    public boolean upload(HttpSession httpSession,@RequestBody MultipartFile file){
        return true;
    }

    @RequestMapping(value = "/searchproducts.do")
    public Result<PageBean<List<ProductWithDescAndHot>>> searchProducts(HttpSession httpSession,@RequestBody @Nullable Integer pageNum, @RequestBody @Nullable Integer pageSize, @RequestBody @Nullable Integer id, @RequestBody String name){
        if(pageNum==null)pageNum=1;
        if(pageSize==null)pageSize=10;
        //id参数可能多余？
        //只实现了按名搜索
        return productServiceImp.searchProducts(pageNum,pageSize,id,name);
    }

    @RequestMapping(value = "/getdetail.do")
    public Result<Product> getDetail(HttpSession httpSession,@RequestBody int productId){
        return null;
    }

    @RequestMapping(value = "/setstatus.do")
    public Result<Product> setStatus(HttpSession httpSession,@RequestBody int productId,@RequestBody int status,@RequestBody int hot){
        return null;
    }

    @RequestMapping(value = "/saveproduct.do")
    public Result<Product> saveProduct(HttpSession httpSession,
                                       @RequestBody String name,
                                       @RequestBody int productId,
                                       @RequestBody int partsId,
                                       @RequestBody String detail,
                                       @RequestBody String specParam,
                                       @RequestBody BigDecimal price,
                                       @RequestBody int stock,
                                       @RequestBody String subImages){
        return null;
    }

}
