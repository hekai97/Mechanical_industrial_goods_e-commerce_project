define(['jquery','handlebar','common'],function(jquery,Handlebars,common){
    //1.获取订单编号
    var orderNo = common.getParam("orderNo");
    var orderAmount=common.getParam("orderAmount");
    //$("#order_num").html(rs.data.orderNo);
    //alert(orderNo);
    //alert(orderNo);
    //2.获取订单详情
	function getDetail(){
        $("#order_num").html(orderNo);
        $("#pay_1").html(orderAmount);
        $("#pay_2").html(orderAmount);
         $.ajax({
            url:baseUrl+"alipay/pay.do",
            xhrFields:{withCredentials:true},
			crossDomain:true,
            data:{"out_trade_no":orderNo,"subject":orderNo+orderAmount,"total_amount":orderAmount},
            type:"post",
            success:function(rs){
                
            }
        });

		// $.ajax({
		// 	url:baseUrl+"order/getdetail.do",
		// 	xhrFields:{withCredentials:true},
		// 	crossDomain:true,
		// 	data:{"orderNo":orderNo},
		// 	type:'get',
		// 	success:function(rs){
				//判断方法是否正确
				//if(rs.status==0){
					//订单信息
					
                    //orderAccount=re.data.amount;

					// $("#order-status-container").html(rs.data.statusDesc);
					// $("#order-ptype-container").html(rs.data.typeDesc);
					// $("#order-paytime-container").html(rs.data.paymentTime);
					//拼接地址信息
					// var address = rs.data.address.province+" "
					//  	+rs.data.address.city+" "
					//  	+rs.data.address.addr+" "
					//  	+rs.data.address.zip+" "
					//  	+rs.data.address.name+" "
					//  	+rs.data.address.mobile+" ";
					// $("#address-container").html(address);
					// //商品订单列表
					// $("#item-container").html();
					// var tpl = $("#product-item-tpl").html();
					// var func = Handlebars.compile(tpl);
					// var result = func(rs.data.orderItems);
					// $("#item-container").html(result);
					// //支付 取消 确认收货按钮显示判断
					// if(rs.data.status!=1){
					// 	$("#order_pay").remove();
					// 	$("#order_cancel").remove();
					// }
			// 	}else{
			// 		alert(rs.msg);
			// 	}
			// }
		// });
	}

    return{
		getDetail:getDetail
	};

});