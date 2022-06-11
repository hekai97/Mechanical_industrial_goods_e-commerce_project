package com.example.mechanical_industrial_goods_eommerce_project_for_android.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.SearchAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.PageBean;
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

public class SearchActivity extends AppCompatActivity {

    private MaterialRefreshLayout refreshLayout;
    private SverResponse<PageBean<Product>> result;
    private List<Product> mDatas;
    private String typeId="0";
    private String productTypeId="";
    private String searchKey="";
    private EditText search_edit;
    private RecyclerView recyclerView;

    private SearchAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        initView();
        bindRefreshListener();

        Intent intent=getIntent();
        searchKey=intent.getStringExtra("searchKey");
        productTypeId=intent.getStringExtra("productTypeId");
        if(!TextUtils.isEmpty(searchKey)||!TextUtils.isEmpty(productTypeId))
        {
            search_edit.setText(searchKey);
            if(TextUtils.isEmpty(productTypeId)) {
//                findProductByParams(typeId,1,10,searchKey,true);
                findProductByParams("0", 1, 10, searchKey, true);
            } else {
                findProductByParams("0",1,10,searchKey,true);
            }
        }


    }

    private void initView() {
        mDatas=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.product_rv);
        refreshLayout=(MaterialRefreshLayout) findViewById(R.id.refresh_layout);

        search_edit=(EditText)findViewById(R.id.search_edit);
        search_edit.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId==EditorInfo.IME_ACTION_SEARCH)
                {
                    //Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
                    searchKey=search_edit.getText().toString();
//                    findProductByParams(typeId,1,10,searchKey,true);
                    findProductByParams("0",1,10,searchKey,true);
                    //收起软键盘
                    InputMethodManager imm = (InputMethodManager) SearchActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken() , 0);
                }
                return false;
            }
        });
        adapter=new SearchAdapter(mDatas,SearchActivity.this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String id=mDatas.get(pos).getId()+"";
                Intent intent=new Intent(SearchActivity.this,DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int pos) {

            }
        });
    }
    private void bindRefreshListener()
    {
        refreshLayout.setLoadMore(true);

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新
                refreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //super.onRefreshLoadMore(materialRefreshLayout);
                //上拉加载更多
                if(result!=null&&result.getStatus()== ResponseCode.SUCCESS.getCode())
                {
                    PageBean pageBean=result.getData();
                    if(pageBean.getPageNum()!=pageBean.getNextPage())
                    {
                        findProductByParams(typeId,pageBean.getNextPage(),pageBean.getPageSize(),searchKey,false);

                    }
                    else {
                        refreshLayout.finishRefreshLoadMore();
                        Toast.makeText(SearchActivity.this,"已经到底了",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // TODO: 2022/6/12 有问题，使用当前接口缺少参数，找不到数据
    private void findProductByParams(String productTypeId, int pageNum, int pageSize, String name,final boolean flag)
    {
        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PRODUCT_URL)
                .addParams("name",name)
                .addParams("productTypeId",productTypeId)
                .addParams("partsId",0+"")
                .addParams("pageNum",pageNum+"")
                .addParams("pageSize",pageSize+"")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("searchResult:"+response);
                        final Type type= new TypeToken<SverResponse<PageBean<Product>>>(){}.getType();
                        result= JsonUtils.fromJson(response,type);
                        if(result.getStatus()==ResponseCode.SUCCESS.getCode())
                        {
                            if(null!=result.getData())
                            {
                                if(flag) mDatas.clear();
                                mDatas.addAll(result.getData().getData());
                               adapter.notifyDataSetChanged();
                            }
                            if(!flag)
                            {
                                refreshLayout.finishRefreshLoadMore();
                            }
                        }
                    }
                });
    }
}
