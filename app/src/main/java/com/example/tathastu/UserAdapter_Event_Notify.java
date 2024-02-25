package com.example.tathastu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class UserAdapter_Event_Notify extends RecyclerView.Adapter<UserAdapter_Event_Notify.EventViewHolder> {

    private List<UserModel_Event_Notify> eventList;

    public UserAdapter_Event_Notify(List<UserModel_Event_Notify> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event_notify, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        UserModel_Event_Notify event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imgEventBg,imgEventNgo;
        private AppCompatTextView textEventNgo;
        private AppCompatTextView textEventNgoDes;
        private AppCompatTextView textEventNgoAddress;
        private AppCompatTextView textEventNgoDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEventBg=itemView.findViewById(R.id.img_Event_bg);
            imgEventNgo = itemView.findViewById(R.id.img_Event_ngo);
            textEventNgo = itemView.findViewById(R.id.text_Event_ngo);
            textEventNgoDes = itemView.findViewById(R.id.text_Event_ngo_des);
            textEventNgoAddress = itemView.findViewById(R.id.text_Event_ngo_address);
            textEventNgoDate = itemView.findViewById(R.id.text_Event_ngo_date);
        }

        public void bind(UserModel_Event_Notify event) {
            imgEventNgo.setImageResource(event.getNgoImageResId());
            textEventNgo.setText(event.getNgoName());
            textEventNgoDes.setText(event.getNgoDescription());
            textEventNgoAddress.setText(event.getNgoAddress());
            textEventNgoDate.setText(event.getEventDate());
        }
    }
}
