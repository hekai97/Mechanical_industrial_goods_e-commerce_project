package com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Param;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class IndexBannerAndParamAdapter extends DelegateAdapter.Adapter<IndexBannerAndParamAdapter.BannerAndParamViewHolder> {

    public static final int TYPE_HEADER=0;
    public static final int TYPE_NORMAL=1;

    private View bannerView;
    private List<Param> mData;
    private Context context;
    private LayoutHelper layoutHelper;

    public IndexBannerAndParamAdapter(List<Param> mData, Context context, LayoutHelper layoutHelper) {
        this.mData = mData;
        this.context = context;
        this.layoutHelper = layoutHelper;
    }

    public void setBannerView(View bannerView){
        this.bannerView = bannerView;
    }

    /**
     * 根据位置返回不同的视图
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if(this.bannerView == null){
            return TYPE_NORMAL;
        }
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.layoutHelper;
    }

    @NonNull
    @Override
    public IndexBannerAndParamAdapter.BannerAndParamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(bannerView != null && viewType == TYPE_HEADER){
            return new BannerAndParamViewHolder(bannerView);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_index_params_list_item,null,false);

        return new BannerAndParamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexBannerAndParamAdapter.BannerAndParamViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_HEADER){
            return;
        }
        final int pos = getRealPosition(holder);
        holder.tv.setText(mData.get(pos).getName());
    }

    private int getRealPosition(RecyclerView.ViewHolder holder){
        int pos = holder.getLayoutPosition();
        return bannerView == null?pos:pos-1;
    }

    @Override
    public int getItemCount() {
        return bannerView == null?mData.size():mData.size()+1;
    }


    public static class BannerAndParamViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public BannerAndParamViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
