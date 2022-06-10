package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;

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

    private long verificationCode=0; //生成的验证码
    private String email; //邮箱

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
        findViewById(R.id.register_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_button:
                //调用注册方法
                register();
                break;
        }
    }

    private void register() {
        String name = nameEdit.getText().toString();
        String num = numEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String passwordConfirm = passwordConfirmEdit.getText().toString();
        String mail = mailEdit.getText().toString();
        String question = questionEdit.getText().toString();
        String answer = answerEdit.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入用户名！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(num)){
            Toast.makeText(this,"请输入手机号！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(passwordConfirm)){
            Toast.makeText(this,"请输入确认密码！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this,"请输入邮箱！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(question)){
            Toast.makeText(this,"请输入找回密码的问题！",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(answer)){
            Toast.makeText(this,"请输入找回密码问题的答案！",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Objects.equals(password, passwordConfirm)){
            Toast.makeText(this,"确认密码和密码不一致！",Toast.LENGTH_LONG).show();
            passwordConfirmEdit.setText("");
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
    /*private void sendVerificationCode(final String email) {
        try {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        RandomNumber rn = new RandomNumber();
                        verificationCode = rn.getRandomNumber(6);
                        SendEmail se = new SendEmail(email);
                        se.sendHtmlEmail(verificationCode);//发送html邮件
                        Toast.makeText(RegisterActivity.this,"发送成功",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}