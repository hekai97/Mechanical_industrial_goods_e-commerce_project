package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.lang.annotation.ElementType;
import java.lang.reflect.Type;

import okhttp3.Call;

public class ResetPwActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_resetpw_name;
    private EditText et_resetpw_new;
    private EditText et_resetpw_question;
    private EditText et_resetpw_ans;
    private String[] params;
    private String Userquestion;
    private Integer UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("params");
        Userquestion = bundle.getString("question");
        UserId = bundle.getInt("userid");
        Log.d("questiont", "questiont:"+Userquestion);
        Log.d("questiont", "questiont:"+UserId);
        setContentView(R.layout.activity_reset_pw);
        et_resetpw_name = (EditText) findViewById(R.id.et_resetpw_name);
        et_resetpw_new = (EditText) findViewById(R.id.et_resetpw_new);
        et_resetpw_question = (EditText) findViewById(R.id.et_resetpw_question);
        et_resetpw_ans = (EditText) findViewById(R.id.et_resetpw_ans);

        et_resetpw_question.setText(Userquestion+"");

        findViewById(R.id.resetpw_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resetpw_button:
                //验证问题
                String name = et_resetpw_name.getText().toString();
                String newpw= et_resetpw_new.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,"请输入登录账号！",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(newpw)){
                    Toast.makeText(this,"请输入新密码！",Toast.LENGTH_LONG).show();
                }else{
                    checkAns();
                }
                break;
        }
    }

    private void checkAns(){
        String asw = et_resetpw_ans.getText().toString();
        String name = et_resetpw_name.getText().toString();

        OkHttpUtils.post()
                .url(Constant.API.CHECK_ASW_URL)
                .addParams("account",name)
                .addParams("question",Userquestion)
                .addParams("asw",asw)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toast.makeText(ResetPwActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<String>>(){}.getType();
                        SverResponse<String> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            //调用重置方法
                            resetpw();
                        }else{
                            Toast.makeText(ResetPwActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void resetpw() {
        String name = et_resetpw_name.getText().toString();
        String newpw= et_resetpw_new.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入登录账号！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(newpw)){
            Toast.makeText(this,"请输入新密码！",Toast.LENGTH_LONG).show();
            return;
        }

        OkHttpUtils.post()
                .url(Constant.API.REGISTER_RESETPW_URL)
                .addParams("userId", String.valueOf(UserId))
                .addParams("newpwd",newpw)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ResetPwActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            Toast.makeText(ResetPwActivity.this,"密码修改成功！",Toast.LENGTH_SHORT).show();
                          /* Intent intent = new Intent(ResetPwActivity.this,LoginActivity.class);
                            startActivity(intent);*/
                            ResetPwActivity.this.finish();
                        }else{
                            Toast.makeText(ResetPwActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}