package com.example.tathastu.User_Package.Blood_Section;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.Blood_Section.B_Details_of_person;
import com.example.tathastu.User_Package.Blood_Section.B_Home_Screen;

import java.util.ArrayList;
import java.util.List;


// Home page - blood request
public class adapter1 extends RecyclerView.Adapter<adapter1.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, age, mno, loc, bgroup;

    adapter1(B_Home_Screen context, ArrayList<String> name, ArrayList<String> age, ArrayList<String> mno, ArrayList<String> loc, ArrayList<String> bgroup){
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.age = age;
        this.mno = mno;
        this.loc = loc;
        this.bgroup = bgroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.b_person_card_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // bind the textview with data received

        String title = name.get(i);
        viewHolder.textname.setText(title);

        String title2 = age.get(i);
        viewHolder.textage.setText(title2);

        String title3 = mno.get(i);
        viewHolder.textmno.setText(title3);

        String title4 = loc.get(i);
        viewHolder.txtlocation.setText(title4);

        String title5 = bgroup.get(i);
        viewHolder.txttype.setText(title5);
        // similarly you can set new image for each card and descriptions


    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        TextView textname,textage,textmno,txtlocation,txttype;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_name);
            textage = itemView.findViewById(R.id.txt_age);
            textmno = itemView.findViewById(R.id.txt_mno);
            txtlocation = itemView.findViewById(R.id.txt_location);
            txttype = itemView.findViewById(R.id.txt_type);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent i = new Intent(view.getContext(), B_Details_of_person.class);
            i.putExtra("title", name.get(getAdapterPosition()));
            i.putExtra("age", age.get(getAdapterPosition()));
            i.putExtra("mno", mno.get(getAdapterPosition()));
            i.putExtra("loc", loc.get(getAdapterPosition()));
            i.putExtra("bgroup", bgroup.get(getAdapterPosition()));
            view.getContext().startActivity(i);
        }


    }
}



