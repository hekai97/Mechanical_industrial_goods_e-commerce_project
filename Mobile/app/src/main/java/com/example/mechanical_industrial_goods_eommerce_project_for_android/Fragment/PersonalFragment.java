package com.example.mechanical_industrial_goods_eommerce_project_for_android.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.User;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.AddressListActivity;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.LoginActivity;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.ui.OrderListActivity;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.PrimitiveIterator;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    private TextView user;

    //本地广播
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
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
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.LOAD_CART_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                initUserInfo();
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
        super.onHiddenChanged(hidden);
        if (!hidden){
            initUserInfo();
        }
    }

    private void initView(View view) {

        user = (TextView) view.findViewById(R.id.user);
        view.findViewById(R.id.btn_addr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                Intent intent = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到所有订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","0");
                startActivity(intent);
            }
        });


        view.findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                exit();
            }
        });

        view.findViewById(R.id.fg_user_dfk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到待付款订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","1");
                startActivity(intent);
            }
        });

        view.findViewById(R.id.fg_user_dfh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到待付款订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","2");
                startActivity(intent);
            }
        });

        view.findViewById(R.id.fg_user_dsh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到待付款订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","3");
                startActivity(intent);
            }
        });

        view.findViewById(R.id.fg_user_dpj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到待付款订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","4");
                startActivity(intent);
            }
        });

        view.findViewById(R.id.fg_user_sh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转跳转到待付款订单
                Intent intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("status","5");
                startActivity(intent);
            }
        });

    }

    private void exit() {
        OkHttpUtils.post()
                .url(Constant.API.USER_EXIT_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        user.setText("未登录");
                        user.setClickable(true);
                        Intent intent;
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void initUserInfo(){
        OkHttpUtils.get()
                .url(Constant.API.USER_INFO_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result = JsonUtils.fromJson(response,type);
                        Log.d("USER_INFO_URL", String.valueOf(result.getStatus()));
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            user.setText(result.getData().getAccount());
                        }else{
                            //跳转登录界面
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}