package com.example.unidevhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unidevhub.R;
import com.example.unidevhub.activities.UserDetailActivity;
import com.example.unidevhub.models.GlobalCommunity;
import com.example.unidevhub.models.UserData;
import com.example.unidevhub.utils.ItemAnimation;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GlobalCommunityAdapter extends RecyclerView.Adapter<GlobalCommunityAdapter.RecyclerviewHolder> {

    Context context;
    List<GlobalCommunity> userDataList;
    List<GlobalCommunity> filteredUserDataList;

    public GlobalCommunityAdapter(Context context, List<GlobalCommunity> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
        this.filteredUserDataList = userDataList;
    }

    @NonNull
    @Override
    public GlobalCommunityAdapter.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_community, parent, false);
        return new GlobalCommunityAdapter.RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlobalCommunityAdapter.RecyclerviewHolder holder, final int position) {

        holder.userName.setText(filteredUserDataList.get(position).getCommunityName());
        holder.userDesc.setText(filteredUserDataList.get(position).getDescp());
        holder.userImage.setImageResource(filteredUserDataList.get(position).getImageUrl());

        ItemAnimation.animateFadeIn(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("username", filteredUserDataList.get(position).getCommunityName());
                intent.putExtra("userDesc", filteredUserDataList.get(position).getDescp());
                context.startActivity(intent);
            }
        });

        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "User Name Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        holder.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the text of the button to "Joined"
                holder.joinBtn.setText("Joined");
                // Disable the button to prevent further clicks
                holder.joinBtn.setEnabled(false);
                holder.joinBtn.setBackgroundResource(R.drawable.button_bg_join);
            }
        });






    }

    @Override
    public int getItemCount() {
        return filteredUserDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder {


        CircleImageView userImage;
        TextView userName, userDesc;
        Button joinBtn;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userDesc = itemView.findViewById(R.id.userDesc);
            joinBtn = itemView.findViewById(R.id.join_btn);



        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredUserDataList = userDataList;
                }
                else{

                    List<GlobalCommunity> lstFiltered = new ArrayList<>();
                    for(GlobalCommunity row: userDataList){
                        if(row.getCommunityName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredUserDataList = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredUserDataList = (List<GlobalCommunity>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }

}
