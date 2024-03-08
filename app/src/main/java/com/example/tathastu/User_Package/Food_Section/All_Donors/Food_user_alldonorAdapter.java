package com.example.tathastu.User_Package.Food_Section.All_Donors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.NGO_Package.NGO_Food_Camp.Donor.NGO_food_user_request_inDetails;
import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


// Home screen/Home screen2 - camp card
public class Food_user_alldonorAdapter extends RecyclerView.Adapter<Food_user_alldonorAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, number, email, loc, note;


    Food_user_alldonorAdapter(Food_user_all_donors context, ArrayList<String> name, ArrayList<String> number, ArrayList<String> email, ArrayList<String> loc, ArrayList<String> note) {
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.number = number;
        this.email = email;
        this.loc = loc;
        this.note = note;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.cardview_food_donor_request,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data received
        String title = name.get(i);
        viewHolder.textname.setText(title);

        String title2 = number.get(i);
        viewHolder.textnumber.setText(title2);

        String title4 = loc.get(i);
        viewHolder.textloc.setText(title4);

        String title5 = note.get(i);
        viewHolder.textnote.setText(title5);

        viewHolder.BTN_food_donor_delete.setVisibility(View.GONE);
        // similarly you can set new image for each card and descriptions

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        TextView textname,textnumber,textloc,textnote;
        FloatingActionButton BTN_food_donor_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_food_donor_name);
            textnumber = itemView.findViewById(R.id.txt_food_donor_mno);
            textloc = itemView.findViewById(R.id.txt_food_donor_location);
            textnote = itemView.findViewById(R.id.txt_food_donor_fooddetails);
            BTN_food_donor_delete=itemView.findViewById(R.id.BTN_food_donor_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent i = new Intent(view.getContext(), Food_user_allDonors_indetails.class);
            i.putExtra("name", name.get(getAdapterPosition()));
            i.putExtra("number", number.get(getAdapterPosition()));
            i.putExtra("email", email.get(getAdapterPosition()));
            i.putExtra("loc", loc.get(getAdapterPosition()));
            i.putExtra("note", note.get(getAdapterPosition()));
            view.getContext().startActivity(i);
        }

    }
}


