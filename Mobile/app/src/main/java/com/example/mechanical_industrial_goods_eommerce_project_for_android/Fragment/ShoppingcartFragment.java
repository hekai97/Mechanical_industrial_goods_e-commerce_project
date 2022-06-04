package com.example.mechanical_industrial_goods_eommerce_project_for_android.Fragment;

import android.app.UiAutomation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.ToastUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.CartAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Cart;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.CartItem;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.ConfirmOrderActivity;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.DetailActivity;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.LoginActivity;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingcartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingcartFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<CartItem> mData;
    private CartAdapter cartAdapter;

    private TextView total;
    private TextView btn_buy;
    private CheckBox checkBox;
    private  boolean isEdit =false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //本地广播
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;


    public ShoppingcartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingcartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingcartFragment newInstance(String param1, String param2) {
        ShoppingcartFragment fragment = new ShoppingcartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        InitView(view);
//        onHiddenChanged(false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // 注册广播
        Log.d("tiaozhuanlogin","zhuceguangbo");
        localBroadcastManager = localBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.LOAD_CART_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("tiaozhuanlogin","onReceive");
                //加载购物车数据
                loadCartData();
            }
        };
        localBroadcastManager.registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d("tiaozhuanlogin", String.valueOf(hidden));
        super.onHiddenChanged(hidden);
        Log.d("tiaozhuanlogin", String.valueOf(hidden));
        if(!hidden){
            loadCartData();
        }
    }

    public void InitView(View view){

        recyclerView = (RecyclerView)view.findViewById(R.id.cart_rv);
        total = (TextView)view.findViewById(R.id.total);
        btn_buy = (TextView)view.findViewById(R.id.buy_btn);
        checkBox = (CheckBox) view.findViewById(R.id.btn_checked);

        mData = new ArrayList<>();
        cartAdapter = new CartAdapter(getActivity(),mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);

        cartAdapter.setOnCartOptListener(new CartAdapter.OnCartOptListener() {
            @Override
            public void updateProductCount(int productId, int count) {
                updateProduct(productId,count);
            }

            @Override
            public void delProductFromCart(int productId) {
                delProductById(productId);
            }
        });

        view.findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit){
                    isEdit =false;
                    for(CartItem item : mData){
                        item.setEdit(true);
                    }
                }else {
                    isEdit = true;
                    for(CartItem item : mData){
                        item.setEdit(false);
                    }
                }

                cartAdapter.notifyDataSetChanged();
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到确定订单页面
                System.out.println("buy");
                if(!total.getText().toString().equals("合计：￥0"))
                {
                    Intent intent=new Intent(getActivity(), ConfirmOrderActivity.class);
                    startActivity(intent);
                }
                else
                {
                    ToastUtils.showLongToast(getActivity(),"请先选择商品");
                }

            }
        });

        cartAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //跳转到详情界面
                //提取产品编号并跳转
                String id=mData.get(pos).getId()+"";
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }


    //加载购物车数据
    private void loadCartData(){
        Log.d("tiaozhuanlogin","loadcartdata");
        OkHttpUtils.get()
                .url(Constant.API.CART_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JsonUtils.fromJson(response,type);

                        Log.d("tiaozhuanlogin",result.getStatus()+"");

                        if (result.getStatus() == ResponseCode.SUCCESS.getCode()){
                            if(result.getData().getLists() != null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                            total.setText("合计：￥" + result.getData().getTotalPrice());
                        }else {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }

                    }
                });

    }

    private void updateProduct(int productId,int count) {
        OkHttpUtils.get()
                .url(Constant.API.CART_UPDATE_URL)
                .addParams("productId",productId+"")
                .addParams("count",count+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            if(result.getData().getLists()!=null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                               total.setText("合计：￥"+result.getData().getTotalPrice());
                        }
                    }
                });
    }

    //删除商品
    private void delProductById(int productId){
        OkHttpUtils.get()
                .url(Constant.API.CART_DEL_URL)
                .addParams("productId",productId+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            if(result.getData().getLists()!=null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                            total.setText("合计：￥"+result.getData().getTotalPrice());
                        }
                    }
                });
    }
}