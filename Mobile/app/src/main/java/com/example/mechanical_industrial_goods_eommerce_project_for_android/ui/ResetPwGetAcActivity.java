package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.ToastUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Cart;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.User;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

public class ResetPwGetAcActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_resetpw_sub_ac_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw_get_ac);
        et_resetpw_sub_ac_name = (EditText) findViewById(R.id.et_resetpw_sub_ac_name);
        findViewById(R.id.resetpw_sub_ac_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resetpw_sub_ac_button:
                checkUserAc();
                break;
        }
    }

    private void checkUserAc() {
        String name = et_resetpw_sub_ac_name.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入登录账号！",Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils.post()
                .url(Constant.API.USER_QUE_URL)
                .addParams("account",name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ResetPwGetAcActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result = JsonUtils.fromJson(response,type);
//                        Log.d("question","getData:"+result);
                        if(result.getStatus() == ResponseCode.SUCCESS.getCode()){
//                            Log.d("question","getData:"+result.getData());
                            Intent intent = new Intent(ResetPwGetAcActivity.this,ResetPwActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("question",result.getData().getQuestion());
                            bundle.putInt("userid",result.getData().getId());
                            intent.putExtra("params",bundle);

                            startActivity(intent);
                            ResetPwGetAcActivity.this.finish();
                        }else{
                            Toast.makeText(ResetPwGetAcActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}