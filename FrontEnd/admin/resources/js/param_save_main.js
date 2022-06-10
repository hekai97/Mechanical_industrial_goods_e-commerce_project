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
			 'adminlte':['bootstrap'],
	    }
 });

require(['jquery','bootstrap','datatables.net','bsdataTables'
	,'adminlte','simple-module','uploader','hotkeys','simditor','webuploader','handlebars-v4.0.11','common','param_save'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte,module,uploader,hotkeys,simditor,webuploader,handlebars,common,param_save){
	$(function(){
		//用户校验
		common.userCalibration();
		//1.获得产品类型参数
		param_save.getParams();
		//2.保存
		param_save.saveBtn();
	});
});