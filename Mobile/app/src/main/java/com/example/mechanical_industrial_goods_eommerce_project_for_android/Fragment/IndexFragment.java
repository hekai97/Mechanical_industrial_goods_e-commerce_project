package com.example.mechanical_industrial_goods_eommerce_project_for_android.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.IndexActAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.IndexBannerAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.IndexBannerAndParamAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters.IndexHotProductAdapter;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.databinding.FragmentIndexBinding;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.IndexBannerBean;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Param;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.ResponseCode;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.SverResponse;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.utils.JsonUtils;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.utils.getScreenParams;
import com.google.gson.FieldAttributes;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndexFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentIndexBinding binding;



    private IndexBannerAndParamAdapter indexBannerAndParamAdapter;
    private IndexHotProductAdapter indexHotProductAdapter;
    private DelegateAdapter delegateAdapter;//定义代理适配器

    Banner banner;
    private List<Param> categoryData; //产品类型
    private List<Product> productData; //热销商品

    private final int PARAM_ROW_COL = 3;

    private RecyclerView recyclerView;

    public IndexFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndexFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
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
        //View view = inflater.inflate(R.layout.fragment_index,container,false);
        binding = FragmentIndexBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        initView(view);
        loadParams();
        loadHotProducts();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*useBanner();*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    /*public void useBanner() {
        binding.banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new IndexBannerAdapter(IndexBannerBean.getTestData2()))
                .setIndicator(new CircleIndicator(getActivity()));
    }*/

    public void initView(View view){

        recyclerView = binding.rv;
        //产品分类参数
        categoryData = new ArrayList<>();
        productData = new ArrayList<>();

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();

        /*Banner*/

        View BannerView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index_banner,null,false);
        Banner banner = (Banner) BannerView.findViewById(R.id.banner);
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getScreenParams.getScreenHeight(getActivity())/4));

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(PARAM_ROW_COL);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0){
                    return PARAM_ROW_COL;
                }else{
                    return 1;
                }

            }
        });

        indexBannerAndParamAdapter = new IndexBannerAndParamAdapter(categoryData,getActivity(),gridLayoutHelper);
        indexBannerAndParamAdapter.setBannerView(banner);
        adapters.add(indexBannerAndParamAdapter);

        /*活动区*/

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(com.example.mechanical_industrial_goods_eommerce_project_for_android.utils.DpToPx.dpTopx(getActivity(),20));
        adapters.add(new IndexActAdapter(getActivity(),linearLayoutHelper));

        /*Hot*/

        LinearLayoutHelper hotLayoutHelper = new LinearLayoutHelper();
        indexHotProductAdapter = new IndexHotProductAdapter(productData,getActivity(),hotLayoutHelper);
        adapters.add(indexHotProductAdapter);
        //点击热销产品，要跳转到详情页面



        delegateAdapter = new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        recyclerView.setAdapter(delegateAdapter);

        //启动轮播图
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new IndexBannerAdapter(IndexBannerBean.getTestData2()))
                .setIndicator(new CircleIndicator(getActivity()));
    }

    private void loadParams(){

        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PARAM_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        final Type type = new TypeToken<SverResponse<List<Param>>>(){}.getType();
                        SverResponse<List<Param>> result = JsonUtils.fromJson(response,type);
                        if(result.getStatus()== ResponseCode.SUCCESS.getCode()){
                            if(result.getData()==null){
                                return;
                            }

                            if ((result.getData().size()&PARAM_ROW_COL)==0){
                                categoryData.addAll(result.getData());
                            }else {
                                int count = result.getData().size()/PARAM_ROW_COL;
                                categoryData.addAll(result.getData().subList(0,count*PARAM_ROW_COL));
                            }
                            indexBannerAndParamAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }


    private void loadHotProducts(){

        OkHttpUtils.get()
                .url(Constant.API.HOT_PRODUCT_URL)
                .addParams("num","10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<List<Product>>>(){}.getType();
                        SverResponse<List<Product>> result = JsonUtils.fromJson(response,type);
                        if (result.getStatus()==ResponseCode.SUCCESS.getCode()){
                            if (result.getData()!=null){
                                productData.addAll(result.getData());
                                indexHotProductAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}