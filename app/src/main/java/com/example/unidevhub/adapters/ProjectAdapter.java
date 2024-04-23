package com.example.unidevhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unidevhub.R;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Viewholder> {

    private ArrayList<String> items;
    private Context context;

    public ProjectAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_projects,parent,false);

        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Viewholder holder, int position) {
        holder.title.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        TextView title;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
        }
    }
}
