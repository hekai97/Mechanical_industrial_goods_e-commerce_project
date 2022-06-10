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

require(['jquery','bootstrap','datatables.net','bsdataTables','adminlte', 'fnReloadAjax','common','order_detail'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte, fnReloadAjax,common,order_detail){
	$(function(){
		//用户校验
		common.userCalibration();
		//获取订单详情 和商品信息
		order_detail.getOrderDetail();
	});
});