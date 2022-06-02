package com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Product;

import java.security.PublicKey;
import java.util.List;

public class CategoryRightAdapter extends RecyclerView.Adapter<CategoryRightAdapter.ProductViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Product> mData;
    private OnItemClickListener onItemClickListener;

    public CategoryRightAdapter(Context context, List<Product> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_category_right_list_item,null,false);


        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = mData.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice()+"");
        Glide.with(context).load(Constant.API.BASE_URL+product.getIconURL()).into(holder.icon_url);
        //
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(view,(int) view.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public View itemView;
        public TextView name;
        public TextView price;
        public ImageView icon_url;

        public ProductViewHolder(View itemView){

            super(itemView);
            this.itemView = itemView;
            name = (TextView)itemView.findViewById(R.id.name);
            price =(TextView)itemView.findViewById(R.id.price);
            icon_url=(ImageView)itemView.findViewById(R.id.icon_url);
        }
    }
}
