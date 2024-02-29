package com.example.tathastu.User_Package.user_NGO_list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;

import java.util.List;

public class NGODataAdapter extends RecyclerView.Adapter<NGODataAdapter.ViewHolder> {

    private Context context;
    private List<NGOData> dataList;
    private ItemClickListener itemClickListener;

    public NGODataAdapter(Context context, List<NGOData> dataList, ItemClickListener itemClickListener) {
        this.context = context;
        this.dataList = dataList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ngo_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NGOData ngoData = dataList.get(position);

        holder.txtNgoName.setText(ngoData.getNgoName());
        holder.txtMobile.setText(ngoData.getNgoMno());
        holder.txtCategory.setText(ngoData.getNgoCategory());

        holder.txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(ngoData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNgoName, txtMobile, txtCategory, txtSeeAll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgoName = itemView.findViewById(R.id.txt_NGO_name);
            txtMobile = itemView.findViewById(R.id.txt_NGO_mno);
            txtCategory = itemView.findViewById(R.id.txt_NGO_category);
            txtSeeAll = itemView.findViewById(R.id.txt_NGO_seeall);
        }
    }
    public interface ItemClickListener {
        void onItemClick(NGOData ngoData);
    }

}
