require.config({
    paths: {
            "jquery": "../js/jquery-1.6.2.min",
            "handlebar": "../js/handlebars-v4.0.11",
            },
});


require(['jquery','handlebar','common','order_list'],function (jquery,handlebar,common, order_list){
   $(function(){
       //加载登录用户信息
       common.getUserInfo();
       //用户登出
       common.loginOut();
       
       order_list.ready();
       
   });	
});