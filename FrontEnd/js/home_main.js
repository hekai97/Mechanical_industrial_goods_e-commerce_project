require.config({
    paths: {
            "jquery": "../js/jquery-1.6.2.min",
            "jquery_SuperSlide": "../js/jquery.SuperSlide.2.1.1",
            "handlebar": "../js/handlebars-v4.0.11",
            "jquery_url": "../js/jquery.url",
            },
    shim:  {
            'jquery_SuperSlide':['jquery'],
            'jquery_url':['jquery'],
            }
});

require(['jquery','jquery_SuperSlide','handlebar','jquery_url','common','home'], 
       function (jquery,jquery_SuperSlide,handlebar,jquery_url,common, home){
   $(function(){
       //加载登录用户信息
       common.getUserInfo();
       //用户登出
       common.loginOut();
       //1.加载产品分类
       home.getParam();
       //2.加载热销商品
       home.getHotProduct();
       //3.加载楼层信息
       home.getFloors();
   });	
});