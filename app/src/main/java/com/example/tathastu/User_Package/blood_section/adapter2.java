package com.example.tathastu.User_Package.blood_section;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;

import java.util.ArrayList;
import java.util.List;


// Home screen/Home screen2 - camp card
public class adapter2 extends RecyclerView.Adapter<adapter2.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, sdate, edate, mno, loc;

    adapter2(B_Home_Screen context, ArrayList<String> name, ArrayList<String> sdate, ArrayList<String> edate, ArrayList<String> mno, ArrayList<String> loc){
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.sdate = sdate;
        this.edate = edate;
        this.mno = mno;
        this.loc = loc;
    }

    adapter2(B_Home_Screen_2 context, ArrayList<String> name, ArrayList<String> sdate, ArrayList<String> edate, ArrayList<String> mno, ArrayList<String> loc){
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.sdate = sdate;
        this.edate = edate;
        this.mno = mno;
        this.loc = loc;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.b_camp_card_view,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

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
            Intent i = new Intent(view.getContext(),B_Details_of_Camp.class);
            i.putExtra("title", name.get(getAdapterPosition()));
            i.putExtra("sdate", sdate.get(getAdapterPosition()));
            i.putExtra("edate", edate.get(getAdapterPosition()));
            i.putExtra("loc", loc.get(getAdapterPosition()));
            i.putExtra("mno", mno.get(getAdapterPosition()));
            view.getContext().startActivity(i);
        }

    }
}



