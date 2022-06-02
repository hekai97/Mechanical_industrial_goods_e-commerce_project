require.config({
    paths: {
            "jquery": "../js/jquery-1.6.2.min",
            "handlebar": "../js/handlebars-v4.0.11",
            }
});

require(['jquery','handlebar','common','product_search'],function (jquery,handlebar,common, product_search){
   $(function(){
       //加载登录用户信息
       common.getUserInfo();
       //用户登出
       common.loginOut();
       //加载  获取所有产品类型
       product_search.ready();
   });	
});