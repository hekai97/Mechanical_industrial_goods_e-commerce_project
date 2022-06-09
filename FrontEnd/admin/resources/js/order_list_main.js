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

require(['jquery','bootstrap','datatables.net','bsdataTables','adminlte', 'fnReloadAjax','common','order_list'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte, fnReloadAjax,common,order_list){
	$(function(){
		//用户校验
		common.userCalibration();
		//订单表格初始化
		order_list.initialization();
		//加载订单按钮和查询框
		order_list.addBtn();
		//查询订单
		order_list.selectProductInfo();
		
	});
});