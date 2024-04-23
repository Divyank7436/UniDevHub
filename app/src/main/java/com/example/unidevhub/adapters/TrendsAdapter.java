package com.example.unidevhub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.unidevhub.R;
import com.example.unidevhub.models.trends;

import java.util.ArrayList;

public class TrendsAdapter extends RecyclerView.Adapter<TrendsAdapter.Viewholder> {

    public TrendsAdapter(ArrayList<trends> items) {
        this.items = items;
    }

    ArrayList<trends> items;
    Context context;

    @NonNull
    @Override
    public TrendsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_trends,parent,false);

        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendsAdapter.Viewholder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.subtitle.setText(items.get(position).getSubtitle());
        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),"drawable", context.getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView title, subtitle;
        ImageView pic;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTrendText);
            subtitle = itemView.findViewById(R.id.subtitle);
            pic = itemView.findViewById(R.id.trendPic);
        }
    }
}
