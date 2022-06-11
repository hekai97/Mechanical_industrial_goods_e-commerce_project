package com.hekai.back.controller.portal;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.service.ActionProductService;
import com.hekai.back.utils.PageBean;
import com.hekai.back.vo.ActionProductFloorVo;
import com.hekai.back.vo.ActionProductListVo;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ActionProductPortalController {
	@Autowired
	private ActionProductService actionProductService;
	/**
	 * 查询热销商品
	 * @param num
	 * @return
	 */
	@RequestMapping(value="/findhotproducts.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num){
		return actionProductService.findHotProducts(num);
	}
	/**
	 * 查找楼层商品
	 * @return
	 */
	@RequestMapping(value = "/findfloors.do",method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionProductFloorVo> findFloorProducts(){
		return actionProductService.findFloorProducts();
	}
	/**
	 * 根据商品编号获取商品详情
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/getdetail.do",method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionProduct> getProductDetail(Integer productId){
		return actionProductService.findProductDetailForPortal(productId);
	}
	/**
	 * 根据分页条件查询商品
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findproducts.do",method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<PageBean<ActionProductListVo>> searchProducts(Integer productTypeId,Integer partsId, String name,
																	  @RequestParam(value="pageNum",defaultValue="1") int pageNum,
																	  @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		return actionProductService.findProductsForPortal(productTypeId,partsId,name,pageNum,pageSize);
	}
}
