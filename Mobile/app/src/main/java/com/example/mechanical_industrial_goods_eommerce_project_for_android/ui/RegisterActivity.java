package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.User;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEdit;
    private EditText numEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmEdit;
    private EditText mailEdit;
    private EditText questionEdit;
    private EditText answerEdit;
    private EditText verifyCodeEdit;

    private String mail;
    private String verificationCode; //生成的验证码
    private String verificationMail; //给这个邮箱发的验证码
    private long verificationTime = 0; //验证码有效开始时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEdit= (EditText) findViewById(R.id.register_name);
        numEdit= (EditText) findViewById(R.id.register_num);
        passwordEdit= (EditText) findViewById(R.id.register_password);
        passwordConfirmEdit = (EditText) findViewById(R.id.register_password_confirm);
        mailEdit= (EditText) findViewById(R.id.register_mail);
        questionEdit= (EditText) findViewById(R.id.register_question);
        answerEdit= (EditText) findViewById(R.id.register_answer);
        verifyCodeEdit= (EditText) findViewById(R.id.register_mail_verification_code);
        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.btn_mail_verification_code).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_button:
                //调用注册方法
                register();
                break;
            case R.id.btn_mail_verification_code:
                //发送验证码
                long tempTime = System.currentTimeMillis();
                if(tempTime - verificationTime >= 100000){
                    mail = mailEdit.getText().toString();
                    sendVerificationCode(mail);
                }else {
                    Toast.makeText(this,"点击过于频繁，请稍等！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void register() {
        String name = nameEdit.getText().toString();
        String num = numEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String passwordConfirm = passwordConfirmEdit.getText().toString();
        String question = questionEdit.getText().toString();
        String answer = answerEdit.getText().toString();
        String verify_code = verifyCodeEdit.getText().toString();

        long clickVerificationTime = System.currentTimeMillis();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入用户名！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(num)){
            Toast.makeText(this,"请输入手机号！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(num.length() != 11){
            Toast.makeText(this,"请输入正确手机号！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6){
            Toast.makeText(this,"请输入更安全密码！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passwordConfirm)){
            Toast.makeText(this,"请输入确认密码！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"请输入邮箱！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(question)){
            Toast.makeText(this,"请输入找回密码的问题！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(answer)){
            Toast.makeText(this,"请输入找回密码问题的答案！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Objects.equals(password, passwordConfirm)){
            Toast.makeText(this,"确认密码和密码不一致！",Toast.LENGTH_SHORT).show();
            passwordConfirmEdit.setText("");
            return;
        }
        if(TextUtils.isEmpty(verify_code)){
            Toast.makeText(this,"请输入邮箱验证码！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Objects.equals(verify_code, verificationCode)){
            Toast.makeText(this,"邮箱验证码不正确！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Objects.equals(verificationMail, mail)){
            Toast.makeText(this,"邮箱有变更，请重新发送验证码！",Toast.LENGTH_SHORT).show();
            return;
        }
        if(clickVerificationTime > (verificationTime + 300000)){
            Toast.makeText(this,"验证码超时，请重新发送验证码！",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post()
                .url(Constant.API.REGISTER_INFO_URL)
                .addParams("account",name)
                .addParams("phone",num)
                .addParams("password",password)
                .addParams("email",mail)
                .addParams("question",question)
                .addParams("asw",answer)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                           /* Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);*/
                            RegisterActivity.this.finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //发送验证码
    private void sendVerificationCode(String mail) {
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"请输入邮箱！",Toast.LENGTH_SHORT).show();
            return;
        }
        verificationTime = System.currentTimeMillis();
        OkHttpUtils.post()
                .url(Constant.API.MAIL_SEND_URL)
                .addParams("email",mail)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<String>>(){}.getType();
                        SverResponse<String> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            verificationCode = result.getData();
                            Log.d("mailcode",verificationCode+"");
                            verificationMail = result.getMsg();
                            Log.d("mailcode",verificationMail+"");
                            verificationTime = System.currentTimeMillis();
                            Log.d("mailcode",verificationTime+"");
                        }else{
                            Toast.makeText(RegisterActivity.this,"error",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        Toast.makeText(this,"已发送验证码，请注意查收！",Toast.LENGTH_SHORT).show();
    }

}