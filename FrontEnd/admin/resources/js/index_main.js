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


require(['jquery','bootstrap','datatables.net','bsdataTables','adminlte', 'fnReloadAjax','common','index'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte, fnReloadAjax, common, index){
		$(function(){
			//用户校验
			common.userCalibration();
			//1.商品表格初始化
			index.initialProducts();
			//2.新增商品按钮和查询框
			index.addBtn();
			//3.查询商品信息
			index.selectProductInfo();
		});
});