package com.example.mechanical_industrial_goods_eommerce_project_for_android.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.DpToPx;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.SpaceItemDecoration;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.ToastUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.CategoryLeftAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.CategoryRightAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.PageBean;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Param;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.DetailActivity;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.SearchActivity;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    private RecyclerView leftRecycleView;
    private List<Param> leftCategoryData;

    private CategoryLeftAdapter categoryLeftAdapter;

    private RecyclerView rightRecyclerView ;
    private List<Product> rightProductData ;
    private CategoryRightAdapter categoryRightAdapter;

    private MaterialRefreshLayout refreshLayout;

    private SverResponse<PageBean<Product>> result;
//    private List<SverResponse<PageBean<Product>>> resultList;
    private String typeId;
    private String name;
    private Integer partsId;
    private EditText search_edit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        initView(view);
        loadParams();
        bindRefreshListener();
        return view;
    }



    private void initView(View view){

        search_edit=(EditText)view.findViewById(R.id.toolbar_searchview);
        search_edit.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    String searchKey=search_edit.getText().toString();
                    if(!TextUtils.isEmpty(searchKey))
                    {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken() , 0);
                        Intent intent=new Intent(getActivity(), SearchActivity.class);
                        intent.putExtra("searchKey",searchKey);
                        startActivity(intent);
                    }
                    else {
                        ToastUtils.showShortToast(getActivity(),"搜索不能为空");
                        //Toast.makeText(getActivity(),"搜索不能为空",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        //初始化
        leftRecycleView = (RecyclerView) view.findViewById(R.id.category_rv);
        leftCategoryData = new ArrayList<>();
        categoryLeftAdapter = new CategoryLeftAdapter(getActivity(),leftCategoryData);

        rightRecyclerView=(RecyclerView)view.findViewById(R.id.product_rv);
        rightProductData = new ArrayList<>();
        categoryRightAdapter = new CategoryRightAdapter(getActivity(),rightProductData);

        refreshLayout = (MaterialRefreshLayout)view.findViewById(R.id.refresh_layout);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        leftRecycleView.setLayoutManager(linearLayoutManager);
        //设置适配器
        leftRecycleView.setAdapter(categoryLeftAdapter);
        categoryLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                typeId = leftCategoryData.get(pos).getId()+"";
                name = leftCategoryData.get(pos).getName();
                partsId = leftCategoryData.get(pos).getParent_id();
//                findProductByParam(typeId,1,10,true);
                findProductByParam(typeId,1,10,leftCategoryData.get(pos).getName(),true,leftCategoryData.get(pos).getParent_id());

            }

            @Override
            public void onItemLongClick(View v, int pos) {

            }
        });

        categoryRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //跳转到详情界面
                //提取产品编号并跳转
                String id=rightProductData.get(pos).getId()+"";
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int pos) {

            }
        });

        //网格布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rightRecyclerView.addItemDecoration(new SpaceItemDecoration(DpToPx.dpTopx(getActivity(),10),DpToPx.dpTopx(getActivity(),5)));
        rightRecyclerView.setLayoutManager(gridLayoutManager);
        rightRecyclerView.setAdapter(categoryRightAdapter);

    }

    private void bindRefreshListener(){
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新
                refreshLayout.finishRefresh();
            }

            //上拉刷新
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //super.onRefreshLoadMore(materialRefreshLayout);
                if(result!=null && result.getStatus()== ResponseCode.SUCCESS.getCode() && result.getData().getNextPage() > result.getData().getPageNum()){
                    Log.d("loadingMMMMM","notNull1");
                    PageBean pageBean = result.getData();
                    if(pageBean.getPageNum()!=pageBean.getNextPage()){
                        findProductByParam(typeId,pageBean.getNextPage(),pageBean.getPageSize(),name,false,partsId);
                        Log.d("loadingMMMMM","notNull2");
                    }
                }else {
                    Log.d("loadingMMMMM","Null");
                    materialRefreshLayout.finishRefreshLoadMore();
                    ToastUtils.showShortToast(getActivity(),"已经到底了");
                }
            }
        });
    }

    private void loadParams(){

        Log.d("URLAAA",Constant.API.CATEGORY_PARAM_URL);

        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PARAM_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.d("URLAAA", String.valueOf(e));

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.d("URLAAA","SUCCESSSSS");

                        final Type type = new TypeToken<SverResponse<List<Param>>>(){}.getType();
                        SverResponse<List<Param>> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            if(result.getData()==null){
                                return;
                            }
                            leftCategoryData.clear();
                            leftCategoryData.addAll(result.getData());

                            typeId = leftCategoryData.get(0).getId()+"";
                            leftCategoryData.get(0).setPressed(true);
//                            findProductByParam(typeId,1,10,true);
                            findProductByParam(typeId,1,10,leftCategoryData.get(0).getName(),true,leftCategoryData.get(0).getParent_id());
                            categoryLeftAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

    private void findProductByParam(String productTypeId, int pageNum, int pageSize, @Nullable String name, boolean flag, int partsId){
        Log.d("allproduct",name);
        Log.d("allproduct",productTypeId);
        Log.d("allproduct", String.valueOf(pageNum));
        Log.d("allproduct", String.valueOf(pageSize));
        Log.d("allproduct", String.valueOf(partsId));
        OkHttpUtils.post()
                .url(Constant.API.CATEGORY_PRODUCT_URL)
                .addParams("name","")
                .addParams("productTypeId",productTypeId)
                .addParams("pageNum",pageNum+"")
                .addParams("pageSize",pageSize+"")
                .addParams("partsId",partsId+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<PageBean<Product>>>(){}.getType();
                        result = JsonUtils.fromJson(response,type);
//                        final Type type = new TypeToken<List<SverResponse<PageBean<Product>>>>(){}.getType();
//                        resultList = JsonUtils.fromJson(response,type);
//                        result = resultList.get(0);
                        if(result.getStatus()==ResponseCode.SUCCESS.getCode()){

                            Log.d("allproduct","findproductIsOk");

                            if(result.getData()!=null){
                                if(flag){
                                    rightProductData.clear();
                                }

                                rightProductData.addAll(result.getData().getData());
                                categoryRightAdapter.notifyDataSetChanged();
                                refreshLayout.finishRefreshLoadMore();
                            }
                            if (!flag){
                                refreshLayout.finishRefreshLoadMore();
                            }
                        }
                    }
                });
    }

}