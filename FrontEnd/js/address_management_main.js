require.config({
    paths: {
            "jquery": "../js/jquery.min-1.11.3",
            "handlebar": "../js/handlebars-v4.0.11",
            "ChineseDistricts": "../js/distpicker.data",
            "distpicker": "../js/distpicker",
            },
    shim:  {
            'distpicker': ['jquery','ChineseDistricts'],
            }
});

require(['jquery','handlebar','common','address_management'], 
       function (jquery,handlebar,common, address_management){
   $(function(){
       //加载登录用户信息
       common.getUserInfo();
       //用户登出
       common.loginOut();
       
       address_management.ready();
   });	
});