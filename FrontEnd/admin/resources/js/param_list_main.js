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

require(['jquery','bootstrap','datatables.net','bsdataTables','adminlte', 'fnReloadAjax','common','param_list'],
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte, fnReloadAjax,common,param_list){
	$(function(){
		//用户校验
		common.userCalibration();
		//表格初始化
		param_list.productTable();
		//加载按钮
		param_list.addBtn();
	});
});