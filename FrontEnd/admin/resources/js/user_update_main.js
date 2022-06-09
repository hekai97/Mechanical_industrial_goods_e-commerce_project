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
	,'adminlte','simple-module','uploader','hotkeys','simditor','webuploader','common','user_update','fnReloadAjax'], 
		function (jquery,bootstrap,datatables_net,bsdataTables,adminlte,module,uploader,hotkeys,simditor,webuploader,common,user_update,fnReloadAjax){
	$(function(){
		//用户校验
		common.userCalibration();
		//获取用户参数
		user_update.getUserInfo();
		//保存
		user_update.saveUser();
	});
});