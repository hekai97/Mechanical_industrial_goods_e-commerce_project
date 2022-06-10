require.config({
    paths: {
            "jquery": "../js/jquery-1.6.2.min",
            "handlebar": "../js/handlebars-v4.0.11",
            }
});

require(['jquery','handlebar','common','payment'], function (jquery,handlebar,common, payment){
   $(function(){
       //加载用户登陆信息
       common.getUserInfo();
       //用户登出
       common.loginOut();
       
       //cart.ready();
       payment.getDetail();
   });	
});