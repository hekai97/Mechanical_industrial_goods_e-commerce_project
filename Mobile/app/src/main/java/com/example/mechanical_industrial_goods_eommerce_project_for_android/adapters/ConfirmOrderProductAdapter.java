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
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.CartItem;

import java.util.List;


public class ConfirmOrderProductAdapter extends RecyclerView.Adapter<ConfirmOrderProductAdapter.ConfirmOrderViewHolder> {
    private Context context;
    private List<CartItem> mData;
    private boolean showAll = false;        //显示所有产品

    public ConfirmOrderProductAdapter(Context context, List<CartItem> mData) {
        this.context = context;
        this.mData = mData;
    }
    public void setmData(List<CartItem> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override

    public ConfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view= LayoutInflater.from(context).inflate(R.layout.confirm_order_list_item,null,false);
        return new ConfirmOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderViewHolder Holder, int i) {

        CartItem item = mData.get(i);
        if(showAll)
        {
            Holder.name.setText(item.getName());
            Holder.price.setText(item.getPrice() + "");
            Holder.num.setText("X" + item.getQuantity() + "");
            Glide.with(context).load(Constant.API.BASE_URL + item.getIconUrl()).into(Holder.icon_url);
        }
        else {
            if (item.getChecked() == 1 ) {
                Holder.name.setText(item.getName());
                Holder.price.setText(item.getPrice() + "");
                Holder.num.setText("X" + item.getQuantity() + "");
                Glide.with(context).load(Constant.API.BASE_URL + item.getIconUrl()).into(Holder.icon_url);
            }
        }

//                       CartItem item=mData.get(i);
//                       Holder.name.setText(item.getName());
//                       Holder.price.setText(item.getPrice()+"");
//                       Holder.num.setText(item.getQuantity()+"");
//                       Glide.with(context).load(Constant.API.BASE_URL+item.getIconUrl()).into(Holder.icon_url);

    }

    @Override
    public int getItemCount() {
        if(!isShowAll())
        {
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getChecked() == 0) {
                    mData.remove(i);
                }
            }
        }
        return mData.size();
    }
    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public static class ConfirmOrderViewHolder extends RecyclerView.ViewHolder
         {
             public View itemView;
             public ImageView icon_url;
             public TextView name;
             public TextView price;
             public TextView num;
             public ConfirmOrderViewHolder(@NonNull View itemView) {
                 super(itemView);
                 this.itemView=itemView;
                 icon_url=itemView.findViewById(R.id.icon_url);
                 name=itemView.findViewById(R.id.name);
                 price=itemView.findViewById(R.id.price);
                 num=itemView.findViewById(R.id.num);
             }
         }
}
