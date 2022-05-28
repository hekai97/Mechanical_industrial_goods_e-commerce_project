var baseUrl="http://localhost:8080/Mechanical_industrial_goods_e-commerce_project/";
define(function(){
    //获取url中的参数
    function getParam (name){
        //构建一个含有参数的正则表达式
        var reg=new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
        //匹配目标参数
        var r=window.location.search.substring(1).match(reg);
        //返回参数值
        if(r!=null) return decodeURI(r[2]); return null;
    }
    //加载登录用户信息
    function getUserInfo(){
        //向服务器请求数据
        $.ajax({
            url:baseUrl+"user/getuserinfo",
            xhrFields:{withCredential:true},   //允许跨域请求时携带cookie属性
            crossDomain:true,           //允许跨域请求
            success:function*(user){
                //判断是否成功
                if(user.status==0){
                    //隐藏登录时的span标签
                    $("#register_info").css({display:"none"});
                    //显示登陆后span标签
                    $("#login_info").css({display:"block"});
                    //将用户名添加
                    $("#headerUsername").html(user.data.account);
                    //获取用户购物车数量
                    getCartCount();
                }
            }
        });
    }
    
    return{

    };
});