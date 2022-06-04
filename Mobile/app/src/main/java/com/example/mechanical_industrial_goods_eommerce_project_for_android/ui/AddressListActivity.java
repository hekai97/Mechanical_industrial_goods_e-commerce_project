package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.AddressAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Address;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class AddressListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Address> mData;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        findViewById(R.id.btn_add).setOnClickListener(this);
        initView();
        loadAddressList();
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_add:
                // TODO: 2022/6/4 跳转到添加地址activity
                break;
            case R.id.btn_update:
                // TODO: 2022/6/4 跳转到更新地址activity
                break;
        }
    }
    private void initView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.address_rv);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("收货地址列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mData = new ArrayList<>();
        addressAdapter = new AddressAdapter(this,mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addressAdapter);

        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Address address = mData.get(pos);
                Intent intent = new Intent();
                intent.putExtra("address",address);
                setResult(RESULT_OK,intent);
                //销毁自己
                finish();

            }

        });
        addressAdapter.setOnAddrOptListener(new AddressAdapter.OnAddrOptListener() {
            @Override
            public void deleteItem(View v, int pos) {
                String id = mData.get(pos).getId()+"";
                deleteAddress(id);

            }
        });
    }

    /**
     * 加载地址列表
     */
    private void loadAddressList(){
        OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<List<Address>>>(){}.getType();
                        SverResponse<List<Address>> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            mData.clear();
                            mData.addAll(result.getData());
                            addressAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    /**
     * 根据编号删除地址
     * @param id
     */
    private void deleteAddress(String id){
        OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_DEL_URL)
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        SverResponse result = JsonUtils.fromJson(response,SverResponse.class);
                        if(result.getStatus()==ResponseCode.SUCCESS.getCode()){
                            //重新加载数据
                            loadAddressList();
                        }else{
                            Toast.makeText(AddressListActivity.this,result.getMsg(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}