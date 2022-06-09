 require.config({
	 paths: {
		"jquery": "../js/jquery.min",
		"bootstrap":"../js/bootstrap.min",
		"datatables.net":"../js/jquery.dataTables.min",
		"bsdataTables":"../js/dataTables.bootstrap.min",
		"adminlte":"../js/adminlte.min",
		"simple-module":"../js/module",
		"uploader":"../js/uploader",
		"hotkeys":"../js/hotkeys",
		"simditor":"../js/simditor",
		"webuploader":"../js/webuploader",
			 },
	 shim:{
			 'bootstrap':['jquery'],
			 'bsdataTables':['bootstrap'],
			 'fnReloadAjax':['jquery', 'datatables.net'],
			 'adminlte':['bootstrap']
	    }
 });

require(['jquery','bootstrap','datatables.net','bsdataTables'
	,'adminlte','simple-module','uploader','hotkeys','simditor','webuploader','common','param_update'], 
	function (jquery,bootstrap,datatables_net,bsdataTables,adminlte,module,uploader,hotkeys,simditor,webuploader,common,param_update){
	$(function(){
		//用户校验
		common.userCalibration();
		//1.获得产品类型参数
		param_update.getParams();
		//2.保存
		param_update.saveUpdate();
	});
});