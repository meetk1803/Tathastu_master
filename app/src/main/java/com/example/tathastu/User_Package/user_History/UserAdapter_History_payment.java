package com.example.tathastu.User_Package.user_History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;

import java.util.List;

public class UserAdapter_History_payment extends RecyclerView.Adapter<UserAdapter_History_payment.EventViewHolder> {

    private final List<UserModel_History_payment> paymentList;

    public UserAdapter_History_payment(List<UserModel_History_payment> paymentList) {
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_history_payment, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        UserModel_History_payment event = paymentList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView text_history_money;
        private AppCompatTextView text_history_date;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            text_history_money = itemView.findViewById(R.id.text_history_money);
            text_history_date = itemView.findViewById(R.id.text_history_date);
        }

        public void bind(UserModel_History_payment event) {
            text_history_money.setText(event.getMoneyPayment());
            text_history_date.setText(event.getMoneyDate());
        }
    }
}
