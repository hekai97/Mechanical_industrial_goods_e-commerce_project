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

 });

require(['common','product_save','simditor_product','webuploader_product'], 
		function (common,product_save,simditor_product,webuploader_product){
	$(function(){
		//用户校验
		common.userCalibration();
		//1.获取产品类型参数
		product_save.getParams();
		//2.为产品下拉绑定事件
		product_save.dropDownEvent();
		//3.保存商品信息
		product_save.saveBtn();
	});
});