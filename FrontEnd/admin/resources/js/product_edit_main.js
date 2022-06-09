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
			 'simditor_product':['simditor'],
			 'webuploader_product':['webuploader']
	    }
 });

require(['jquery','bootstrap','datatables.net','bsdataTables'
	,'adminlte','simple-module','uploader','hotkeys','simditor','webuploader','handlebars-v4.0.11','common','product_edit','simditor_product','webuploader_product'], function (jquery,bootstrap,datatables_net,bsdataTables,adminlte,module,uploader,hotkeys,simditor,webuploader,handlebars,common,product_edit,simditor_product,webuploader_product){
	$(function(){
		//用户校验
		common.userCalibration();
		//获取产品类型参数和商品信息
		product_edit.getType();
		//为产品下拉绑定事件
		product_edit.dropDownEvent();
		//保存商品信息
		product_edit.btnSave();
	});
});