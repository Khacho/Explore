package com.khachik.explore.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khachik.explore.Models.ArticlesModel;
import com.khachik.explore.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private ArrayList<ArticlesModel> adapterItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView artycleTitle;
        private final TextView artycleCity;
        private final TextView artycleCountry;
        private final CircleImageView artycleImage;
        public ViewHolder(View view) {
            super(view);
            artycleTitle = (TextView) view.findViewById(R.id.artycle_title);
            artycleCity = (TextView) view.findViewById(R.id.artycle_city);
            artycleCountry = (TextView) view.findViewById(R.id.artycle_country);
            artycleImage = (CircleImageView) view.findViewById(R.id.artycle_image);
        }
    }
    public ArticlesAdapter(ArrayList<ArticlesModel> adapterItems) {
        this.adapterItems = adapterItems;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artycles_card, parent, false);
        return new ArticlesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.artycleTitle.setText(adapterItems.get(position).getTitle());
        holder.artycleCity.setText(adapterItems.get(position).getCity());
        holder.artycleCountry.setText(adapterItems.get(position).getCountry());
        holder.artycleImage.setImageResource(R.mipmap.ic_launcher);
    }
    @Override
    public int getItemCount() {
        return adapterItems.size();
    }
}
