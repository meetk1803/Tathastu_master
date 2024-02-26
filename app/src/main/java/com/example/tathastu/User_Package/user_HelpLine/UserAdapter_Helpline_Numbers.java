package com.example.tathastu.User_Package.user_HelpLine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;

import java.util.List;

public class UserAdapter_Helpline_Numbers extends RecyclerView.Adapter<UserAdapter_Helpline_Numbers.HelplineViewHolder> {

    private List<UserModel_Helpline_Numbers> helplineList;
    private Context context;

    public UserAdapter_Helpline_Numbers(List<UserModel_Helpline_Numbers> helplineList, Context context) {
        this.helplineList = helplineList;
        this.context = context;
    }

    @NonNull
    @Override
    public HelplineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_helpline_numbers, parent, false);
        return new HelplineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelplineViewHolder holder, int position) {
        UserModel_Helpline_Numbers helpline = helplineList.get(position);

        // Bind your data to the ViewHolder here
        holder.txt_helpline_name.setText(helpline.getName());
        holder.txt_helpline_mno.setText(helpline.getNumber());
        holder.txt_helpline_category.setText(helpline.getCategory());

        // Set a click listener for the copy button
        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle copy to clipboard action
                copyToClipboard(context, helpline.getNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return helplineList.size();
    }

    public static class HelplineViewHolder extends RecyclerView.ViewHolder {
        // Declare your CardView elements here
        AppCompatTextView txt_helpline_name, txt_helpline_mno, txt_helpline_category;
        ImageButton btn_copy; // Add this line

        public HelplineViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize your CardView elements here
            txt_helpline_name = itemView.findViewById(R.id.txt_helpline_name);
            txt_helpline_mno = itemView.findViewById(R.id.txt_helpline_mno);
            txt_helpline_category = itemView.findViewById(R.id.txt_helpline_category);
            btn_copy = itemView.findViewById(R.id.btn_copy); // Add this line
        }
    }

    private static void copyToClipboard(Context context, String phoneNumber) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Phone Number", phoneNumber);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            showToast(context, "Phone number copied to clipboard");
        }
    }

    private static void showToast(Context context, final String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
