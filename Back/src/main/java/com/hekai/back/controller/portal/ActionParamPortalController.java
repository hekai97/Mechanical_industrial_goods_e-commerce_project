package com.hekai.back.controller.portal;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionParam;
import com.hekai.back.service.ActionParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/param")
public class ActionParamPortalController {
	@Autowired
	private ActionParamsService actionParamsService;
	/**
	 * 查找所有商品类型
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/findallparams.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<List<ActionParam>> findAllParams(HttpSession session){
		return actionParamsService.findAllParams();
	}

}

