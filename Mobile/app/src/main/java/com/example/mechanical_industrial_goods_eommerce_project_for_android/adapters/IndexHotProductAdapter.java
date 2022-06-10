package com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;

import java.util.List;

public class IndexHotProductAdapter extends DelegateAdapter.Adapter<IndexHotProductAdapter.HotProductViewHolder>{

    private List<Product> data;
    private Context context;
    private LayoutHelper layoutHelper;
    private OnItemClickListener onItemClickListener;

    public IndexHotProductAdapter(List<Product> data, Context context, LayoutHelper layoutHelper) {
        this.data = data;
        this.context = context;
        this.layoutHelper = layoutHelper;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.layoutHelper;
    }

    @NonNull
    @Override
    public HotProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_hot_list_item,null,false);
        return new HotProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotProductViewHolder holder, int position) {
        if(position==0){
            holder.titleContainer.setVisibility(View.VISIBLE);
        }else{
            holder.titleContainer.setVisibility(View.GONE);
        }

        Product product = data.get(position);
        holder.name.setText(product.getName());
        holder.price.setText("价格：￥"+product.getPrice());
        holder.stock.setText("库存："+ product.getStock());
        holder.contentContainer.setTag(position);
        Glide.with(context).load(Constant.API.BASE_URL+product.getIconUrl()).into(holder.icon_URL);
        holder.contentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null)
                {
                    onItemClickListener.onItemClick(view,(int) view.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class HotProductViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout titleContainer;
        public RelativeLayout contentContainer;
        public TextView btn_more;
        public TextView name;
        public ImageView icon_URL;
        public TextView stock;
        public TextView price;

        public HotProductViewHolder(@NonNull View itemView) {
            super(itemView);

            titleContainer = (RelativeLayout) itemView.findViewById(R.id.title_container);
            contentContainer = (RelativeLayout) itemView.findViewById(R.id.content);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            stock = (TextView) itemView.findViewById(R.id.stock);
            icon_URL = (ImageView) itemView.findViewById(R.id.icon_url);
            btn_more = (TextView) itemView.findViewById(R.id.btn_more);

        }
    }
}
