package com.example.drawnav;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdapterTv extends RecyclerView.Adapter<AdapterTv.ViewHolder> {
    Context context;
    List<Tv> tvList;

    public AdapterTv(Context context, List<Tv> tvList) {
        this.context = context;
        this.tvList = tvList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_tv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Tv tv = tvList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(40));

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + tv.getPoster_path())
                .apply(requestOptions)
                .placeholder(R.drawable.rectangle_image)
                .into(holder.id_image);

        holder.detail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,DetailTv.class);
                        intent.putExtra("tv",tv);
                        context.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView id_image;
        Button detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_image = itemView.findViewById(R.id.id_image);
            detail = itemView.findViewById(R.id.id_detail);
        }
    }
}
