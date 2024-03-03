package com.example.tathastu.User_Package.blood_section;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


// My Blood Request page
public class adapter3 extends RecyclerView.Adapter<adapter3.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> name, age, mno, loc, bgroup;

    adapter3(B_Request_page context, ArrayList<String> name, ArrayList<String> age, ArrayList<String> mno, ArrayList<String> loc, ArrayList<String> bgroup){
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
        View view = layoutInflater.inflate(R.layout.b_my_request_card_view,viewGroup,false);
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

        FloatingActionButton BTN_edit, BTN_delete;
        TextView textname,textage,textmno,txtlocation,txttype;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textname = itemView.findViewById(R.id.txt_name);
            textage = itemView.findViewById(R.id.txt_age);
            textmno = itemView.findViewById(R.id.txt_mno);
            txtlocation = itemView.findViewById(R.id.txt_location);
            txttype = itemView.findViewById(R.id.txt_type);
            itemView.setOnClickListener(this);

            BTN_edit = itemView.findViewById(R.id.BTN_edit);
            BTN_delete = itemView.findViewById(R.id.BTN_delete);


            BTN_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(),B_Edit_detail_page.class);
                    view.getContext().startActivity(i);
                }
            });


            BTN_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteConfirmationDialog();
                }

                private void showDeleteConfirmationDialog() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Are you sure you want to delete?")
                            .setTitle("Delete Item")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Perform delete operation here
                                    // For example, you can delete the item from your database or perform any other action
                                    dialog.dismiss(); // Dismiss the dialog
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    dialog.dismiss(); // Dismiss the dialog
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }

        @Override
        public void onClick(View view) {
//            int position = getAdapterPosition();
//            Intent i = new Intent(view.getContext(),B_Detail_Home_Screen.class);
//            i.putExtra("title", name.get(getAdapterPosition()));
//            i.putExtra("age", age.get(getAdapterPosition()));
//            view.getContext().startActivity(i);
        }


    }
}



