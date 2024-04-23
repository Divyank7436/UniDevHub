package com.example.unidevhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unidevhub.R;
import com.example.unidevhub.activities.ProjectsActivity;
import com.example.unidevhub.models.Project;

import java.util.ArrayList;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ViewHolder> {
    private ArrayList<Project> items;
    private Context context;

    public OngoingAdapter(ArrayList<Project> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OngoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ongoing, parent, false);
        return new ViewHolder(inflator);
    }


    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getProjectName());
        holder.date.setText(items.get(position).getDate());
        holder.progressBarPercentage.setText(items.get(position).getProgress());
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicPath(),"drawable", context.getPackageName());

        Glide.with(context).load(drawableResourceId).into(holder.pic);
        if (position==0){
            holder.layout.setBackgroundResource(R.drawable.dark_background);
            holder.title.setTextColor(context.getColor(R.color.secondary));
            holder.date.setTextColor(context.getColor(R.color.secondary));
            holder.progressText.setTextColor(context.getColor(R.color.secondary));
            holder.progressBarPercentage.setTextColor(context.getColor(R.color.secondary));
            holder.pic.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.secondary)));




        }else{
            holder.title.setBackgroundResource(R.drawable.light_bg);
            holder.title.setTextColor(context.getColor(R.color.primary));
            holder.date.setTextColor(context.getColor(R.color.primary));
            holder.progressText.setTextColor(context.getColor(R.color.primary));
            holder.progressBarPercentage.setTextColor(context.getColor(R.color.primary));
            holder.pic.setColorFilter(ContextCompat.getColor(context, R.color.primary), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.primary)));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectsActivity.class);
                intent.putExtra("projectName", items.get(position).getProjectName());
                context.startActivity(intent);
            }
        });
       // holder.progressBarPercentage.setText(items.get(position).getProgress());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title,date,progressBarPercentage,progressText;
        ProgressBar progressBar;
        ImageView pic;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout11);
            progressText = itemView.findViewById(R.id.textView9);
            title = itemView.findViewById(R.id.textView8);
            date = itemView.findViewById(R.id.textView2);
            progressBar = itemView.findViewById(R.id.progressBar2);
            progressBarPercentage = itemView.findViewById(R.id.textView10);
                pic = itemView.findViewById(R.id.imageView2);

        }
    }
}
