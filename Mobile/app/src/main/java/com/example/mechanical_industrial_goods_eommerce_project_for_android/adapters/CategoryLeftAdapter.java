package com.example.mechanical_industrial_goods_eommerce_project_for_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mechanical_industrial_goods_eommerce_project_for_android.Listener.OnItemClickListener;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.R;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.config.Constant;
import com.example.mechanical_industrial_goods_eommerce_project_for_android.models.Param;

import org.w3c.dom.Text;

import java.util.List;

public class CategoryLeftAdapter extends RecyclerView.Adapter<CategoryLeftAdapter.CategroyViewHolder> implements View.OnClickListener{

    private Context context;
    private List<Param> mData;
    private OnItemClickListener onItemClickListener;


    public CategoryLeftAdapter(Context context, List<Param> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CategroyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_category_left_list_item,null,false);
        return new CategroyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategroyViewHolder holder, int position) {

        Param param = mData.get(position);
        holder.category_tv.setText(param.getName());
        holder.category_tv.setTag(position);
        if(param.isPressed()){
            holder.category_tv.setBackgroundResource(R.color.font_color);
        }else{
            holder.category_tv.setBackgroundResource(R.color.colorWhite);
        }
        holder.category_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int pos = (int)view.getTag();
        for(int i=0;i<mData.size();i++){
            if(i==pos){
                mData.get(i).setPressed(true);
            }else{
                mData.get(i).setPressed(false);
            }
        }
        notifyDataSetChanged();
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(view,pos);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CategroyViewHolder extends RecyclerView.ViewHolder{

        public TextView category_tv;//name

        public CategroyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_tv = (TextView) itemView.findViewById(R.id.category_tv);
        }
    }
}
