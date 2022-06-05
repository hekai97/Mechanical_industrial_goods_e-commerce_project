package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.User;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEdit;
    private EditText numEdit;
    private EditText proEdit;
    private EditText cityEdit;
    private EditText qvEdit;
    private EditText addressEdit;
    private EditText youbianEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        nameEdit= (EditText) findViewById(R.id.add_name);
        numEdit= (EditText) findViewById(R.id.add_num);
        proEdit= (EditText) findViewById(R.id.add_pro);
        cityEdit= (EditText) findViewById(R.id.add_city);
        qvEdit= (EditText) findViewById(R.id.add_qv);
        addressEdit= (EditText) findViewById(R.id.add_address);
        youbianEdit= (EditText) findViewById(R.id.add_youbian);
        findViewById(R.id.add_addr_button).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_addr_button:
                //调用增加收货人方法
                addaddress();
                break;
        }
    }

    private void addaddress() {
        String name = nameEdit.getText().toString();
        String num = numEdit.getText().toString();
        String pro = proEdit.getText().toString();
        String city = cityEdit.getText().toString();
        String qv = qvEdit.getText().toString();
        String address = addressEdit.getText().toString();
        String youbian=youbianEdit.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入收货人姓名！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(num)){
            Toast.makeText(this,"请输入电话号码！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pro)){
            Toast.makeText(this,"请输入省份！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(city)){
            Toast.makeText(this,"请输入城市！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(qv)){
            Toast.makeText(this,"请输入区！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(address)){
            Toast.makeText(this,"请输入详细地址！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(youbian)){
            Toast.makeText(this,"请输入邮编！",Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils.post()
                .url(Constant.API.USER_ADDR_ADD_URL)
                .addParams("name",name)
                .addParams("mobile",num)
                .addParams("province",pro)
                .addParams("city",city)
                .addParams("district",qv)
                .addParams("addr",address)
                .addParams("zip",youbian)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddAddressActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddAddressActivity.this,AddressListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            Toast.makeText(AddAddressActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                            AddAddressActivity.this.finish();
                        }else{
                            Toast.makeText(AddAddressActivity.this,"添加失败！",Toast.LENGTH_SHORT).show();
                            Toast.makeText(AddAddressActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}