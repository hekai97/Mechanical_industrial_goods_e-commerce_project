package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.bumptech.glide.Glide;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView icon_url;
    private TextView name;
    private TextView parts;
    private TextView price;
    private WebView product_detail;
    private EditText num;
    private TextView stock;
    private Toolbar toolbar;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

        //提取其他界面串过来的产品编号
        Intent intent =getIntent();
        String id=intent.getStringExtra("id");
        if(!TextUtils.isEmpty(id))
        {
            loadProductById(id);
        }

    }

    private void initView(){
        icon_url=(ImageView)findViewById(R.id.icon_url);
        name=(TextView)findViewById(R.id.name);
        parts=(TextView)findViewById(R.id.parts);
        price=(TextView)findViewById(R.id.price);
        product_detail=(WebView)findViewById(R.id.product_detail);
        stock=(TextView)findViewById(R.id.stock);
        num=(EditText) findViewById(R.id.edit_num);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("配件详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.btn_jian).setOnClickListener(this);
        findViewById(R.id.btn_jia).setOnClickListener(this);
        findViewById(R.id.buy_btn).setOnClickListener(this);
        findViewById(R.id.cart_btn).setOnClickListener(this);

        num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String stock=editable.toString();
                if(!TextUtils.isEmpty(stock))
                {
                    int inputNum=Integer.valueOf(stock);
                    {
                        if(inputNum>product.getStock())
                        {
                            num.setText(product.getStock()+"");
                        }
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        int inputNum=Integer.valueOf(num.getText().toString());
        switch(view.getId())
        {
            case R.id.cart_btn:
                addProduct2cart();
                break;
            case R.id.buy_btn:
                //buy();
                break;
            case R.id.btn_jia:
                if(inputNum+1<=product.getStock())
                {
                    num.setText((inputNum+1)+"");
                }
                break;
            case R.id.btn_jian:
                if(inputNum-1>=1)
                {
                    num.setText((inputNum-1)+"");
                }
                break;
        }
    }

    private void loadProductById(String id) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        OkHttpUtils.post()
                .url(Constant.API.PRODUCT_DETAIL_URL)
                .addParams("productId",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type=new TypeToken<SverResponse<Product>>(){}.getType();
                        SverResponse<Product> result= JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()) {
                            if (result.getData() == null){
                                return;
                            }
                            product = result.getData();
                            //显示配件信息
                            Glide.with(DetailActivity.this).load(Constant.API.BASE_URL + product.getIconUrl())
                                    .into(icon_url);
                            name.setText(product.getName());
                            parts.setText("配件类型" + product.getPartsId());
                            price.setText("$" + product.getPrice());
                            stock.setText("库存" + product.getStock());
                            num.setText("1");
                            product_detail.loadDataWithBaseURL(Constant.API.BASE_URL,
                                    product.getDetail(),
                                    "text/html",
                                    "utf-8",
                                    null);
                        }
                        else
                        {
                            DetailActivity.this.finish();
                        }
                    }
                });
    }
    private void addProduct2cart(){
        if(product!=null)
        {
            OkHttpUtils.post()
                    .url(Constant.API.CART_ADD_URL)
                    .addParams("productId",product.getId()+"")
                    .addParams("count",num.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            SverResponse result=new JsonUtils().fromJson(response,SverResponse.class);
                            if(result.getStatus()==ResponseCode.SUCCESS.getCode())
                            {
                                Toast.makeText(DetailActivity.this,result.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(DetailActivity.this,result.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}