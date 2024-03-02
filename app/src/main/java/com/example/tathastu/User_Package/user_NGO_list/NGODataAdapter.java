package com.example.tathastu.User_Package.user_NGO_list;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tathastu.R;
import com.example.tathastu.User_Package.user_HelpLine.UserAdapter_Helpline_Numbers;

import java.util.List;

public class NGODataAdapter extends RecyclerView.Adapter<NGODataAdapter.ViewHolder> {

    private Context context;
    private List<NGOData> dataList;
    private int expandedPosition = -1;

    public NGODataAdapter(List<NGOData> dataList,Context context) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NGODataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_ngo_list, parent, false);
        return new NGODataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NGOData ngoData = dataList.get(position);

        holder.txtNgoName.setText(ngoData.getNgoName());
        holder.txtNGOaddress.setText(ngoData.getNgoAddress());
        holder.txtCategory.setText(ngoData.getNgoCategory());
        holder.txtMobile.setText(ngoData.getNgoMno());
        holder.txtNGOwebsite.setText(ngoData.getNgoWebsite());
        holder.txtNGOemail.setText(ngoData.getNgoEmail());
        holder.txtNGOinstagram.setText(ngoData.getNgoInstagram());
        holder.txtNGOlinkedin.setText(ngoData.getNgoLinkedIn());
        holder.txtNGOfacebook.setText(ngoData.getNgoFacebook());
        holder.txtNGOtwitter.setText(ngoData.getNgoTwitter());
        holder.txtNGOyoutube.setText(ngoData.getNgoYoutube());
        // Set a click listener for the helpline name to handle expansion
        holder.txtNgoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExpansion(holder, holder.getAdapterPosition());
            }
        });

        // Handle expansion logic
        if (holder.getAdapterPosition() == expandedPosition) {
            // Show expanded details
            holder.layout_NGO.setVisibility(View.VISIBLE);
            // Set the up arrow drawable
            holder.txtNgoName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_up_24, 0);
        } else {
            // Hide expanded details
            holder.layout_NGO.setVisibility(View.GONE);
            // Set the down arrow drawable
            holder.txtNgoName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.round_keyboard_arrow_down_24, 0);
        }
    }

    private void handleExpansion(NGODataAdapter.ViewHolder holder, int position) {
        if (expandedPosition == position) {
            // Collapse the expanded item
            expandedPosition = -1;
            notifyItemChanged(position);
        } else {
            // Expand the clicked item
            int oldExpandedPosition = expandedPosition;
            expandedPosition = position;
            notifyItemChanged(oldExpandedPosition);
            notifyItemChanged(position);
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNgoName, txtNGOaddress, txtCategory, txtMobile, txtNGOwebsite, txtNGOemail, txtNGOinstagram, txtNGOlinkedin, txtNGOfacebook, txtNGOtwitter, txtNGOyoutube;
        LinearLayout layout_NGO; // Add this line

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNgoName = itemView.findViewById(R.id.txt_NGO_name);
            txtNGOaddress = itemView.findViewById(R.id.txt_NGO_address);
            txtCategory = itemView.findViewById(R.id.txt_NGO_category);
            txtMobile = itemView.findViewById(R.id.txt_NGO_mno);
            txtNGOwebsite = itemView.findViewById(R.id.txt_NGO_website);
            txtNGOemail = itemView.findViewById(R.id.txt_NGO_email);
            txtNGOinstagram = itemView.findViewById(R.id.txt_NGO_instagram);
            txtNGOlinkedin = itemView.findViewById(R.id.txt_NGO_linkedin);
            txtNGOfacebook = itemView.findViewById(R.id.txt_NGO_facebook);
            txtNGOtwitter = itemView.findViewById(R.id.txt_NGO_twitter);
            txtNGOyoutube = itemView.findViewById(R.id.txt_NGO_youtube);
            layout_NGO = itemView.findViewById(R.id.layout_NGO);
            Linkify.addLinks(txtNGOaddress, Linkify.MAP_ADDRESSES);

            // Set OnClickListener for the address TextView
            txtNGOaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle redirection to map or location here
                    redirectToMap(txtNGOaddress.getText().toString());
                }
            });
        }

        private void redirectToMap(String address) {
            // Implement your logic to open the map or location using the address
            // You can use Intent to open a map application or a web link
            // For example, using Google Maps
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
                itemView.getContext().startActivity(mapIntent);
            } else {
                showToast(itemView.getContext(), "Google Maps app not installed.");
            }
        }

        private void showToast(Context context, final String text) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }


}
