require.config({
	 paths: {
			 "jquery": "../js/jquery.min",
			 "bootstrap":"../js/bootstrap.min",
			 "datatables.net":"../js/jquery.dataTables.min",
			 "bsdataTables":"../js/dataTables.bootstrap.min",
			 "adminlte":"../js/adminlte.min",
			 },
	 shim:{
			 'bootstrap':['jquery'],
			 'bsdataTables':['bootstrap'],
			 'fnReloadAjax':['jquery', 'datatables.net'],
			 'adminlte':['bootstrap']
	    }
 });

require(['jquery','bootstrap','datatables.net','bsdataTables'
	,'adminlte','common','user_management','fnReloadAjax'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte,common,user_management,fnReloadAjax){
	$(function(){
		//用户校验
		common.userCalibration();
		//商品表格初始化 和内部功能
		user_management.initialization();
	});
});