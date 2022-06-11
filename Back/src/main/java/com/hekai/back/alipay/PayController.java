package com.hekai.back.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.hekai.back.common.SverResponse;
import com.hekai.back.service.ActionOrderService;
import com.hekai.back.vo.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code @author:} hekai
 * {@code @Date:} 2022/6/8
 */
@Controller
@RequestMapping(value = "/alipay")
public class PayController {

    @Autowired
    private ActionOrderService actionOrderService;
    //wap:QUICK_WAP_WAY
    //web:FAST_INSTANT_TRADE_PAY
    private static final String PRODUCT_CODE = "QUICK_WAP_WAY";
    //表单页面

    @RequestMapping("/home")
    public String index(ModelMap modelMap) {
        return "home/home";
    }
    //调起支付
    @RequestMapping(value = "/pay.do",method = RequestMethod.POST)
    @ResponseBody
    public void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getParameter("out_trade_no");
        // 付款金额，必填
        String total_amount = request.getParameter("total_amount");
        // 订单名称，必填
        String subject = request.getParameter("subject");
        // 商品描述，可空
        String body = request.getParameter("body");
        AlipayClient client = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.APP_ID, AliPayConfig.APP_PRIVATE_KEY, AliPayConfig.FORMAT, AliPayConfig.CHARSET, AliPayConfig.ALIPAY_PUBLIC_KEY,AliPayConfig.sign_type);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        String timeout_express="2m";

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(PRODUCT_CODE);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AliPayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AliPayConfig.return_url);

        // form表单生成
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();

            System.out.println("form:");
            System.out.println(form);

            response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);
            response.getWriter().write(form);//将表单html写到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    //支付完成后的返回
    @RequestMapping("/return.do")
    @ResponseBody
    public String returnCall(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        System.out.println("params");
        System.out.println(params);
        System.out.println("\n验签开始.....\n");

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.sign_type); //调用SDK验证签名
        if (signVerified) {
            ////////////    设置用户订单状态
            actionOrderService.updateOrderToSuccessPay(Long.parseLong(params.get("out_trade_no")));
            ////////////
            System.out.println("return sign  success");
            return "验证签名成功，现在跳转到订单详情页面";
        } else {
            System.out.println("return sign  failed");
            return "验证签名失败";
        }
    }

    /*
    *
    *
    * TRADE_SUCCESS状态代表了充值成功，也就是说钱已经进了支付宝（担保交易）或卖家（即时到账）；
    * 这时候，这笔交易应该还可以进行后续的操作（比如三个月后交易状态自动变成TRADE_FINISHED），
    * 因为整笔交易还没有关闭掉，也就是说一定还有主动通知过来。
    * 而TRADE_FINISHED代表了这笔订单彻底完成了，不会再有任何主动通知过来了。

    综上所述，收到TRADE_FINISHED请求后，这笔订单就结束了，支付宝不会再主动请求商户网站了；
    * 收到TRADE_SUCCESS请求后，后续一定还有至少一条通知记录，即TRADE_FINISHED。
    * 所以，在做通知接口时，切记使用判断订单状态用或的关系
    *
    *
    * */    //异步通知
    @RequestMapping(value = "/notify.do",method = RequestMethod.POST)
    @ResponseBody
    public String notifyCall(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        // 获取支付宝反馈信息
        System.out.println("进入异步通知");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        System.out.println("params:");
        System.out.println(params);

        String tradeStatus = params.get("trade_status");
        System.out.println("tradeStatus:");
        System.out.println(tradeStatus);

        System.out.println("\n验签开始.....\n");
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.sign_type); //调用SDK验证签名

        if (signVerified) {
            System.out.println("notify sign  success");
            /*
            if(trade_status.equals("TRADE_FINISHED")){
            } else if (trade_status.equals("TRADE_SUCCESS")){
            }
             */
            ////////////    设置用户订单状态
            new Thread(() -> {
                System.out.println(params.get("out_trade_no"));

                actionOrderService.updateOrderToSuccessPay(Long.parseLong(params.get("out_trade_no")));
            }).start();

            ////////////
            return "success";
        } else {
            System.out.println("notify sign  failed");
            return "fail";
        }
    }
    @RequestMapping(value = "/getorderinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public SverResponse<String> getOrderInfo(PayOrder order){
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("app_id=").append(AliPayConfig.APP_ID);
//        stringBuilder.append("method=").append(AliPayConfig.APP_ID);
//        stringBuilder.append("&out_trade_no=").append(order.getOut_trade_no());
//        stringBuilder.append("&subject=").append(order.getSubject());
//        stringBuilder.append("&total_amount=").append(order.getTotal_amount());
//        stringBuilder.append("&charset=").append("utf-8");
//        stringBuilder.append("&seller_id=").append("2088621959359744");
//        stringBuilder.append("&product_code=").append(PRODUCT_CODE);
//        stringBuilder.append("&timeout_express=").append("2m");
//        stringBuilder.append("&sign=").append(AliPayConfig.ALIPAY_PUBLIC_KEY);
//        stringBuilder.append("&sign_type=").append(AliPayConfig.sign_type);
//        if(order.getBody()!=null) {
//            stringBuilder.append("&body=").append(order.getBody());
//        }
//
//        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
//
//        String timeout_express="2m";
//
//        // 封装请求支付信息
//        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
//        model.setOutTradeNo(order.getOut_trade_no());
//        model.setSubject(order.getSubject());
//        model.setTotalAmount(order.getTotal_amount().toString());
//        model.setTimeoutExpress(timeout_express);
//        model.setProductCode(PRODUCT_CODE);
//        alipay_request.setBizModel(model);
//        // 设置异步通知地址
//        alipay_request.setNotifyUrl(AliPayConfig.notify_url);
//        // 设置同步地址
//        alipay_request.setReturnUrl(AliPayConfig.return_url);
//        System.out.println(alipay_request);
//        System.out.println(stringBuilder);
//        return SverResponse.createRespBySuccess(stringBuilder.toString());


//        SverResponse<String> sverResponse= SverResponse.createRespBySuccess(OrderInfoUtil.buildOrderParam(OrderInfoUtil.buildOrderParamMap(AliPayConfig.APP_ID,order.getOut_trade_no(),true)));
//        System.out.println(sverResponse.getData());
//        SverResponse<String> result=SverResponse.createRespBySuccess("alipay_sdk=alipay-sdk-java-dynamicVersionNo&"+sverResponse.getData());
//        return result;
        Map<String, String> params = OrderInfoUtil.buildOrderParamMap(AliPayConfig.APP_ID, order,true);
        String orderParam = OrderInfoUtil.buildOrderParam(params);

        String sign = OrderInfoUtil.getSign(params, AliPayConfig.APP_PRIVATE_KEY, true);
        final String orderInfo = orderParam + "&" + sign;
        System.out.println(orderInfo);
        return SverResponse.createRespBySuccess(AliPayConfig.mobile_url,orderInfo);
    }

    @RequestMapping("/mobileresult.do")
    @ResponseBody
    public SverResponse<String> mobileResult(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            System.out.println(name+"   "+valueStr);
            params.put(name, valueStr);
        }
        System.out.println("params");
        System.out.println(params);
        Pattern pattern=Pattern.compile("out_trade_no\":\"[0-9]*\"");
        Matcher matcher = pattern.matcher(params.get("out_trade_no"));
        if(matcher.find()){
            String s1=matcher.group(0);
            String[] res=s1.split(":");
            String orderNo=res[1].substring(1,res[1].length()-1);
            System.out.println(orderNo);
            actionOrderService.updateOrderToSuccessPay(Long.parseLong(orderNo));
            return SverResponse.createRespBySuccessMessage("success");
        }
        return SverResponse.createByErrorMessage("fail");
    }
}
