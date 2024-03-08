package com.example.tathastu.NGO_Package.NGO_Food_Camp.History;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


// Home screen/Home screen2 - camp card
public class NGO_food_camp_historyadapter extends RecyclerView.Adapter<NGO_food_camp_historyadapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, sdate, edate, mno, loc;


    NGO_food_camp_historyadapter(NGO_food_camp_history context, ArrayList<String> name, ArrayList<String> sdate, ArrayList<String> edate, ArrayList<String> mno, ArrayList<String> loc){
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
        View view = layoutInflater.inflate(R.layout.cardview_ngo_food,viewGroup,false);
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
        FloatingActionButton BTN_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_name);
            textsdate = itemView.findViewById(R.id.txt_startdate);
            textedate = itemView.findViewById(R.id.txt_enddate);
            textmno = itemView.findViewById(R.id.txt_mno);
            textlocation = itemView.findViewById(R.id.txt_location);
            BTN_delete = itemView.findViewById(R.id.BTN_delete);
            itemView.setOnClickListener(this);

            BTN_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteConfirmationDialog();

                }

                // Show exit confirmation dialog
                private void showDeleteConfirmationDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.custom_delete_dialog, null);
                    builder.setView(dialogView);

                    ExtendedFloatingActionButton btnExitYes = dialogView.findViewById(R.id.BTN_exit_yes);
                    ExtendedFloatingActionButton btnExitNo = dialogView.findViewById(R.id.BTN_exit_no);


                    final AlertDialog dialog = builder.create();

                    btnExitYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(layoutInflater.getContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    btnExitNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle 'No' button click
                            dialog.dismiss();
                        }
                    });

                    dialog.setCancelable(false); // Prevent dismiss on outside touch
                    dialog.show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent i = new Intent(view.getContext(), NGO_food_camp_history_indetails.class);
            i.putExtra("title", name.get(getAdapterPosition()));
            i.putExtra("sdate", sdate.get(getAdapterPosition()));
            i.putExtra("edate", edate.get(getAdapterPosition()));
            i.putExtra("loc", loc.get(getAdapterPosition()));
            i.putExtra("mno", mno.get(getAdapterPosition()));
            view.getContext().startActivity(i);
        }
    }
}



