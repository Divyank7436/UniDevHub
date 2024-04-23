package com.example.unidevhub.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unidevhub.R;
import com.example.unidevhub.models.Community;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.Viewholder> {

    private ArrayList<Community> items;

    public CommunityAdapter(ArrayList<Community> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CommunityAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_communities, parent, false);
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAdapter.Viewholder holder, int position) {
        holder.title.setText(items.get(position).getCommunityName());
        holder.status.setText(items.get(position).getCommunityStatus());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
            TextView title, status;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.communityName);
            status = itemView.findViewById(R.id.communityStatus);
        }
    }
}
