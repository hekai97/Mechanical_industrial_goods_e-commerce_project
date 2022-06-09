require.config({
	 paths: {
			 "jquery": "../js/jquery.min",
			 "bootstrap":"../js/bootstrap.min",
			 "datatables.net":"../js/jquery.dataTables.min",
			 "bsdataTables":"../js/dataTables.bootstrap.min",
			 "adminlte":"../js/adminlte.min"
			 },
	 shim:{
			 'bootstrap':['jquery'],
			 'bsdataTables':['bootstrap'],
			 'fnReloadAjax':['jquery', 'datatables.net'],
			 'adminlte':['bootstrap']
	    }
 });

require(['adminlte','common','login'],function(adminlte,common,login){
	//登录方法1
	$(function(){
		//用户校验
		common.userCalibration();
		//绑定登录按钮点击事件
		login.clickLogin();
	});
});