package com.example.applicationbannertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements {

    Banner banner;
    List<String> stringList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner = findViewById(R.id.banner);
        stringList.add("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg");
        stringList.add("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg");
        stringList.add("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg");
        banner.setAdapter(new BannerImageAdapter<String>(stringList) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                //加载图片
                Glide.with(holder.itemView)
                        .load(data)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        });
        //设置指示点
        banner.setIndicator(new CircleIndicator(this));

    }


    @Override
    public FruitViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.furit_bannner_item, parent, false);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindView(FruitViewHolder holder, LNBEBean data, int position, int size) {
        holder.imageView.setImageResource(data.getImg());
        holder.textView.setText(data.getName());
    }


}