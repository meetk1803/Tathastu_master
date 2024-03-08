package com.example.tathastu.NGO_Package.NGO_Education_Camp.History;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.NGO_Package.NGO_Food_Camp.History.NGO_food_camp_history_indetails;
import com.example.tathastu.NGO_Package.NGO_Food_Camp.History.NGO_food_camp_historyadapter;
import com.example.tathastu.R;

import java.util.ArrayList;
import java.util.List;

public class NGO_edu_camp_historyAdapter extends RecyclerView.Adapter<NGO_edu_camp_historyAdapter.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<String> name, sdate, edate, mno, loc;


    NGO_edu_camp_historyAdapter(Context context, ArrayList<String> name, ArrayList<String> sdate, ArrayList<String> edate, ArrayList<String> mno, ArrayList<String> loc){
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.sdate = sdate;
        this.edate = edate;
        this.mno = mno;
        this.loc = loc;
    }

    @NonNull
    @Override
    public NGO_edu_camp_historyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.cardview_ngo_edu_history,viewGroup,false);
        return new NGO_edu_camp_historyAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NGO_edu_camp_historyAdapter.ViewHolder viewHolder, int i) {

        // bind the textview with data received
        String title = name.get(i);
        viewHolder.textname.setText(title);

        String title2 = sdate.get(i);
        viewHolder.textsdate.setText(title2);

        String title3 = edate.get(i);
        viewHolder.textedate.setText(title3);

        String title4 = mno.get(i);
        viewHolder.textmno.setText(title4);

        String title5 = loc.get(i);
        viewHolder.textlocation.setText(title5);

        // similarly you can set new image for each card and descriptions

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        TextView textname,textsdate,textedate,textmno,textlocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_name);
            textsdate = itemView.findViewById(R.id.txt_startdate);
            textedate = itemView.findViewById(R.id.txt_enddate);
            textmno = itemView.findViewById(R.id.txt_mno);
            textlocation = itemView.findViewById(R.id.txt_location);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent i = new Intent(view.getContext(), NGO_edu_camp_historyIndetails.class);
            i.putExtra("title", name.get(getAdapterPosition()));
            i.putExtra("sdate", sdate.get(getAdapterPosition()));
            i.putExtra("edate", edate.get(getAdapterPosition()));
            i.putExtra("loc", loc.get(getAdapterPosition()));
            i.putExtra("mno", mno.get(getAdapterPosition()));
            view.getContext().startActivity(i);
        }

    }
}



