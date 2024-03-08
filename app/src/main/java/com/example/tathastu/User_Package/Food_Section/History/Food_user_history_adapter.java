package com.example.tathastu.User_Package.Food_Section.History;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Food_user_history_adapter extends RecyclerView.Adapter<Food_user_history_adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, type, mno, loc;

    Food_user_history_adapter(Food_user_History context, ArrayList<String> name, ArrayList<String> type, ArrayList<String> mno, ArrayList<String> loc) {
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.type = type;
        this.mno = mno;
        this.loc = loc;
    }

    @NonNull
    @Override
    public Food_user_history_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.food_request_card, viewGroup, false);
        return new Food_user_history_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Food_user_history_adapter.ViewHolder viewHolder, int i) {
        // bind the textview with data received

        String title = name.get(i);
        viewHolder.textname.setText(title);

        String title2 = type.get(i);
        viewHolder.txttype.setText(title2);

        String title3 = mno.get(i);
        viewHolder.textmno.setText(title3);

        String title4 = loc.get(i);
        viewHolder.txtlocation.setText(title4);

        // similarly you can set new image for each card and descriptions

    }

    @Override
    public int getItemCount() {
        return name.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textname, txttype, textmno, txtlocation;
        FloatingActionButton BTN_edit, BTN_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_name);
            textmno = itemView.findViewById(R.id.txt_mno);
            txtlocation = itemView.findViewById(R.id.txt_location);
            txttype = itemView.findViewById(R.id.txt_type);

            itemView.setOnClickListener(this);

            BTN_edit = itemView.findViewById(R.id.BTN_edit);
            BTN_delete = itemView.findViewById(R.id.BTN_delete);

            BTN_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), Food_user_history_edit_request.class);
                    view.getContext().startActivity(i);
                }
            });


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
        public void onClick(View v) {

        }
    }
}