package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.ConfirmOrderProductAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Address;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Cart;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.CartItem;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Order;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ConfirmOrderActivity extends AppCompatActivity {

    private TextView name;
    private TextView mobile;
    private TextView addr_detail;
    private TextView total;
    private RecyclerView recyclerView;
    private ConfirmOrderProductAdapter confirmOrderProductAdapter;
    private List<CartItem> cartItems;
    private Address defaultAddr;

    private static final int REQ_ADDR_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        //初始化默认地址
        initDefaultAddr();
        initCartProducts();
    }

    private void initView(){
        name= (TextView) findViewById(R.id.name);
        mobile= (TextView) findViewById(R.id.mobile);
        addr_detail= (TextView) findViewById(R.id.addr_detail);
        recyclerView= (RecyclerView) findViewById(R.id.cart_rv);
        total= (TextView) findViewById(R.id.total);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("确认订单信息");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cartItems=new ArrayList<>();
        confirmOrderProductAdapter=new ConfirmOrderProductAdapter(this,cartItems);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(confirmOrderProductAdapter);

        //提交订单
        findViewById(R.id.buy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();
            }
        });
        //选择地址
        findViewById(R.id.address_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ConfirmOrderActivity.this,AddressListActivity.class);
                startActivityForResult(intent,REQ_ADDR_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADDR_CODE) {
            if (resultCode == RESULT_OK) {
                defaultAddr = (Address) data.getSerializableExtra("address");
                displayInfo();
            }
        }
    }

    /*显示地址信息*/
    private void displayInfo(){
        name.setText(defaultAddr.getName());
        mobile.setText(defaultAddr.getMobile());
        addr_detail.setText(
                defaultAddr.getProvince()+" "
                        +defaultAddr.getCity() +" "
                        +defaultAddr.getDistrict()+" "
                        +defaultAddr.getAddr()
        );
    }

    /*加载默认地址*/
    private void initDefaultAddr(){
        OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_LIST_URL)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<SverResponse<List <Address>>>(){}.getType();
                SverResponse<List<Address>> result=new JsonUtils().fromJson(response,type);
                if(result.getStatus()== ResponseCode.SUCCESS.getCode())
                {
                    if(result.getData()!=null)
                    {
                        for(Address adr: result.getData() )
                        {
                            defaultAddr=adr;
                            break;
                        }
                    }
                    if(defaultAddr==null)
                    {
                        defaultAddr=result.getData().get(0);
                    }
                    displayInfo();
                }
                else {
                    name.setText(defaultAddr.getName());
                    addr_detail.setText("");
                    mobile.setText("请选择收件地址");
                }
            }
        });

    }



    /*加载购物车数据*/
    private void initCartProducts()
    {
        OkHttpUtils.get()
                .url(Constant.API.CART_LIST_URL)
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<SverResponse<Cart>>(){}.getType();
                SverResponse<Cart> result = JsonUtils.fromJson(response,type);
                if(result.getStatus()==ResponseCode.SUCCESS.getCode())
                {
                    if(result.getData().getLists()!=null)
                    {
                        cartItems.clear();
                        cartItems.addAll(result.getData().getLists());
                        confirmOrderProductAdapter.notifyDataSetChanged();
                    }
                    total.setText("总计"+result.getData().getTotalPrice());
                }
            }
        });
    }

    private void submitOrder(){
        if(defaultAddr==null)
        {
            Toast.makeText(this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            return ;
        }
        OkHttpUtils.post()
                .url(Constant.API.ORDER_CREATED_URL)
                .addParams("addrId",defaultAddr.getId()+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type type=new TypeToken<SverResponse<Order>>(){}.getType();
                        SverResponse<Order> result=JsonUtils.fromJson(response,type);
                        result.getData().setDeliverName("王群");
                        if(result.getStatus()==ResponseCode.SUCCESS.getCode())
                        {
                            //跳转到订单详情
                            Toast.makeText(ConfirmOrderActivity.this,"提交订单完成",Toast.LENGTH_SHORT).show();
                            // ConfirmOrderActivity.this.finish();

                            Intent intent = new Intent(Constant.ACTION.LOAD_CART_ACTION);
                            LocalBroadcastManager.getInstance(ConfirmOrderActivity.this).sendBroadcast(intent);

                            intent = new Intent(ConfirmOrderActivity.this, OrderDetailActivity.class);

                            intent.putExtra("id",result.getData().getOrderNo());
                            intent.putExtra("total_amount",result.getData().getAmount());

                            startActivity(intent);


                            ConfirmOrderActivity.this.finish();
                        }
                        else
                        {
                            Toast.makeText(ConfirmOrderActivity.this,result.getStatus(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

}