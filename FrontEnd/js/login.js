define(['common'],function(common){
    var isAccountValidate=false;
    var isPasswordValidate=false;
    //1.失去光标时验证用户名
    function accountCheck(){
        $("#username").blur(function(){
            isAccountValidate=checkAccount();
        });
    }
    //2.失去光标时验证密码
    function pwdCheck(){
        $("#password").blur(function(){
            isPasswordValidate=checkpwd();
        });
    }
    //登录
    function loginBtn(){
        //创建单击事件
        $(".login_btn").click(function(){
            //判断验证信息
            if(!isAccountValidate){
                return checkAccount();
            }
            if(!isPasswordValidate){
                return checkpwd();
            }
            //成功进入接口登录
            $.ajax({
                url:baseUrl+"user/do_login",
                type:"post",
                data:{account:$("#username").val(),password:$("#password").val()},
                xhrFields:{withCredential:true},   //允许跨域请求时携带cookie属性
                crossDomain:true,           //允许跨域请求
                success:function(data){
                    //判断是否登录成功
                    if(data.status==0){
                        //成功判断是否是管理员
                        if(data.data.role==2){
                            $(window).attr("location","html/admin/index.html");
                        }
                        else{
                            window.location.href="../html/index.html";
                        }
                    }else{
                        $("#passwordError").css({display:"block"});
                        $("#passwordError").html(data.msg);
                    }
                    //
                }
            });
        });
    }



    return {
        accountCheck:accountCheck,
        pwdCheck:pwdCheck,
        loginBtn:loginBtn
    };
    
    //检查用户名
    function checkAccount(){
       //获取用户名
        var account=$("#username").val();
        $("#usernameError").css({display:"none"});
        if(account==""){
            $("#usernameError").css({display:"block"});
            $("#usernameError").html("用户名不能为空！");
            return false;
        }
        return true;
    }

    //检查密码
    function checkpwd(){
        //获取输入密码
        var pwd=$("#password").val();
        $("#passwordError").css({display:"none"});
        if(pwd==""){
            $("#passwordError").css({display:"block"});
            $("#passwordError").html("密码不能为空！");
            return false;
        }
        return true;
    }

});