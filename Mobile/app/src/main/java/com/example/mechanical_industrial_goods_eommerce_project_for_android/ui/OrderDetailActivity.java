package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.ConfirmOrderProductAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.CartItem;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Order;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.OrderItem;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView orderNo;
    private TextView created;
    private TextView type;
    private TextView status;
    private TextView deliveryName;
    private TextView total;
    private TextView buy_btn;
    private RecyclerView recyclerView;
    private String total_amount;

    private ConfirmOrderProductAdapter adapter;

    private SverResponse<Order> result;
    private Order order;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_deatail);
        initView();

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        total_amount = intent.getStringExtra("total_amount");
        if(!TextUtils.isEmpty(id))
        {
            loadData(id);
        }


    }

    private void initView() {

        orderNo=(TextView)findViewById(R.id.orderNo_D);
        recyclerView=(RecyclerView)findViewById(R.id.order_detail_rv);
        created=(TextView)findViewById(R.id.created_D);
        type=(TextView)findViewById(R.id.type_D);
        status=(TextView)findViewById(R.id.status_D);
        deliveryName=(TextView)findViewById(R.id.deliveryName_D);
        total=(TextView)findViewById(R.id.total);
        buy_btn=(TextView)findViewById(R.id.buy_btn);

        cartItems=new ArrayList<>();
        adapter=new ConfirmOrderProductAdapter(this,cartItems);
        adapter.setShowAll(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2022/6/5 跳转到支付页
                /*Intent intent;
                intent = new Intent(OrderDetailActivity.this, ZhifuActivity.class);
                startActivity(intent);*/
                pay();
            }
        });
    }

    private void pay(){
        OkHttpUtils.post()
                .url(Constant.API.PAY_URL)
                .addParams("out_trade_no",order.getOrderNo())
                .addParams("subject",order.getDeliverName()+"的订单")
                .addParams("total_amount", String.valueOf(order.getAmount()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type=new TypeToken<SverResponse<String>>(){}.getType();
                        SverResponse<String> result= JsonUtils.fromJson(response,type);
                        Log.d("orderInfo",result.getData());
                        Intent intent = new Intent(OrderDetailActivity.this,PayActivity.class);
                        intent.putExtra("orderInfo",result.getData());
                        intent.putExtra("notify_url",result.getMsg());
                        intent.putExtra("total_amount",total_amount);
                        startActivity(intent);
                    }
                });
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.baidu.com/")));

    }



    private void loadData(String orderNo)
    {
        OkHttpUtils.get()
                .url(Constant.API.OEDER_DETAIL_URL)
                .addParams("orderNo",orderNo)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        System.out.println("orderDetail:"+response);
                        Type type=new TypeToken<SverResponse<Order>>(){}.getType();
                        result= JsonUtils.fromJson(response,type);
                        if(result.getStatus() == ResponseCode.SUCCESS.getCode())
                        {
                            cartItems.clear();
                            cartItems.addAll(orderItemsToCartItems(result.getData().getOrderItems()));
                            adapter.notifyDataSetChanged();
                            order=result.getData();
                            updateHeader();

                        }
                        else {
                            Toast.makeText(OrderDetailActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void updateHeader() {
        deliveryName.setText("收货人:"+order.getDeliverName());
        orderNo.setText("订单编号:"+order.getOrderNo());
        created.setText("订单时间:"+order.getCreated());

//        type.setText("支付类型:"+order.getType());
        switch (order.getType())
        {
            case 1:
                type.setText("支付类型:在线支付");
                break;
            case 2:
                type.setText("支付类型:货到付款");
                break;
        }

        total.setText("合计："+order.getAmount());

//        status.setText("订单状态:"+order.getStatus());

        switch (order.getStatus())
        {
            case 1:
                status.setText("订单状态:未付款");
                break;
            case 2:
                status.setText("订单状态:已付款");
                break;
            case 3:
                status.setText("订单状态:已发货");
                break;
            case 4:
                status.setText("订单状态:交易成功");
                break;
            case 5:
                status.setText("订单状态:交易关闭");
                break;
            case 6:
                status.setText("订单状态:已取消");
                break;
        }

    }

    private List<CartItem> orderItemsToCartItems(List<OrderItem> datas) {

        List<CartItem> cartItems =new ArrayList<>();
        //导订单内商品列表
        for(int i=0;i<datas.size();i++)
        {

            OrderItem orderItem=datas.get(i);
            CartItem cartItem=new CartItem();
            cartItem.setName(orderItem.getGoodsName());
            cartItem.setIconUrl(orderItem.getIconUrl());
            cartItem.setPrice(orderItem.getCurPrice());
            cartItem.setQuantity(orderItem.getQuantity());
            cartItem.setTotalPrice(orderItem.getTotalPrice());

            cartItems.add(cartItem);
        }
        return cartItems;
    }
}