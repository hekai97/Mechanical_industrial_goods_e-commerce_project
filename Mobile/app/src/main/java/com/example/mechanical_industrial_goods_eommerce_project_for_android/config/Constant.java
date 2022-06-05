package com.example.mechanical_industrial_goods_eommerce_project_for_android.config;

public class Constant {

    public static class APIR {

        //基地址
//        public static final String BASE_URL = "http://192.168.43.74:8080/actionmall/";
        public static final String BASE_URL = "http://10.0.2.2:8080/";
        //产品类型参数地址
//        public static final String CATEGORY_PARAM_URL = BASE_URL + "param/findallparams";
        public static final String CATEGORY_PARAM_URL = BASE_URL + "findallparams";
        //热销商品
//        public static final String HOT_PRODUCT_URL = BASE_URL + "product/findhotproducts";
        public static final String HOT_PRODUCT_URL = BASE_URL + "findhotproducts";
        //获取商品列表信息
//        public static final String CATEGORY_PRODUCT_URL = BASE_URL + "product/findproducts";
        public static final String CATEGORY_PRODUCT_URL = BASE_URL + "findproducts";
        //购物车列表
        public static final String  CART_LIST_URL = BASE_URL+"cart/findallcarts";
        //加入购物车
        public static final String CART_ADD_URL = BASE_URL+"cart/savecart";
        //更新购物中商品的数量
        public static final String CART_UPDATE_URL = BASE_URL+"cart/updatecarts";
        //删除购物车中商品
        public static final String CART_DEL_URL = BASE_URL+"cart/delcarts";
        //获取用户信息
        public static final String USER_INFO_URL = BASE_URL+"user/getuserinfo";
        //登陆接口
        public static final String USER_LOGIN_URL= BASE_URL+"user/do_login";
        //商品详情
        public static final String PRODUCT_DETAIL_URL= BASE_URL+"product/getdetail";
        //提交订单
        public static final String ORDER_CREATED_URL= BASE_URL+"order/createorder";
        //地址列表
        public static final String USER_ADDR_LIST_URL= BASE_URL+"addr/findaddrs";
        //删除地址
        public static final String USER_ADDR_DEL_URL= BASE_URL+"addr/deladdr";

    }

    public static class ACTION {
        //加载购物车列表的
        public static final String LOAD_CART_ACTION="cn.techaction.mall.LOAD_CART_ACTION";

    }

    public static class API{
        //基地址
//        public static final String BASE_URL = "http://44.201.174.208/actionmall/";
        public static final String BASE_URL = "http://192.168.43.74:8080/mall/";
        //产品类型参数地址
        public static final String CATEGORY_PARAM_URL= BASE_URL+"param/findallparams.do";
//        public static final String CATEGORY_PARAM_URL = BASE_URL + "findallparams";
        //热销商品
        public static final String HOT_PRODUCT_URL= BASE_URL+"product/findhotproducts.do";
//        public static final String HOT_PRODUCT_URL= BASE_URL+"findhotproducts.do";
        //商品分类查询
        public static final String CATEGORY_PRODUCT_URL= BASE_URL+"product/findproducts.do";
//        public static final String CATEGORY_PRODUCT_URL= BASE_URL+"findproducts.do";
        //商品详情
        public static final String PRODUCT_DETAIL_URL= BASE_URL+"product/getdetail.do";
        //购物车列表
        public static final String  CART_LIST_URL= BASE_URL+"cart/findallcarts.do";
        //加入购物车
        public static final String CART_ADD_URL= BASE_URL+"cart/savecart.do";
        //更新购物中商品的数量
        public static final String CART_UPDATE_URL= BASE_URL+"cart/updatecarts.do";
        //删除购物车中商品
        public static final String CART_DEL_URL= BASE_URL+"cart/delcarts.do";
        //登陆接口
        public static final String USER_LOGIN_URL= BASE_URL+"user/do_login.do";
        //注销接口
        public static final String USER_LOGOUT_URL=BASE_URL+"user/do_logout.do";
        //获取用户信息
        public static final String USER_INFO_URL=BASE_URL+"user/getuserinfo.do";
        //用户退出登录
        public static final String USER_EXIT_URL = BASE_URL+"user/do_logout.do";
        //获取用户密码问题
        public static final String USER_QUE_URL = BASE_URL+"user/getuserquestion.do";

        //用户注册
        public static final String REGISTER_INFO_URL = BASE_URL+"user/do_register.do";
        //用户修改密码
        public static final String REGISTER_RESETPW_URL = BASE_URL+"user/resetpassword.do";
        //检查问题和密码匹配
        public static final String CHECK_ASW_URL = BASE_URL+"user/checkuserasw.do";
        //地址列表
        public static final String USER_ADDR_LIST_URL= BASE_URL+"addr/findaddrs.do";
        //删除地址
        public static final String USER_ADDR_DEL_URL= BASE_URL+"addr/deladdr.do";
        //设置默认地址
        public static final String USER_ADDR_DEFAULT_URL= BASE_URL+"addr/setdefault.do";

        //添加新地址
        public static final String USER_ADDR_ADD_URL=BASE_URL+"addr/saveaddr.do";
        //修改地址
        public static final String USER_UPDATE_ADD_URL=BASE_URL+"addr/findAddressById.do";

        //提交订单
        public static final String ORDER_CREATED_URL= BASE_URL+"order/createorder.do";

        //订单详情
        public static final String OEDER_DETAIL_URL=BASE_URL+"order/getdetail.do";
        //订单列表
        public static final String ORDER_LIST_URL=BASE_URL+"order/getlist.do";
        public static final String ORDER_CANCEL_URL=BASE_URL+"order/cancelorder.do";
    }
}
