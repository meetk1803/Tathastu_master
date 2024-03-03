package com.example.tathastu.Admin_Package.Admin_NGO.Person_User_Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;

import java.util.List;

public class Admin_NGO_p_History_DataAdapter extends RecyclerView.Adapter<Admin_NGO_p_History_DataAdapter.EventViewHolder> {

    private final List<Admin_NGO_p_History_DataModel> paymentList;

    public Admin_NGO_p_History_DataAdapter(List<Admin_NGO_p_History_DataModel> paymentList) {
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ngo_personal_history, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Admin_NGO_p_History_DataModel event = paymentList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView admin_txt_history_campName;
        private AppCompatTextView admin_txt_history_money;
        private AppCompatTextView admin_txt_history_date;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            admin_txt_history_campName = itemView.findViewById(R.id.admin_txt_history_campName);
            admin_txt_history_money = itemView.findViewById(R.id.admin_txt_history_money);
            admin_txt_history_date = itemView.findViewById(R.id.admin_txt_history_date);
        }

        public void bind(Admin_NGO_p_History_DataModel event) {
            admin_txt_history_campName.setText(event.getCampName());
            admin_txt_history_money.setText(event.getMoneyPayment());
            admin_txt_history_date.setText(event.getMoneyDate());
        }
    }
}
