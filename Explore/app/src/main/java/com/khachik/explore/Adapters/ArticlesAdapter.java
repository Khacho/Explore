package com.khachik.explore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khachik.explore.Activities.ArticleActivity;
import com.khachik.explore.Models.ArticlesModel;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private ArrayList<ArticlesModel> adapterItems;
    private Requests request;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        private final TextView artycleTitle;
        private final TextView artycleCity;
        private final TextView artycleCountry;
        private final CircleImageView artycleImage;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            artycleTitle = (TextView) view.findViewById(R.id.artycle_title);
            artycleCity = (TextView) view.findViewById(R.id.artycle_city);
            artycleCountry = (TextView) view.findViewById(R.id.artycle_country);
            artycleImage = (CircleImageView) view.findViewById(R.id.artycle_image);
        }
    }
    public ArticlesAdapter(ArrayList<ArticlesModel> adapterItems, Context context) {
        this.request = new Requests(context);
        this.context = context;
        this.adapterItems = adapterItems;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artycles_card, parent, false);
        return new ArticlesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.artycleTitle.setText(adapterItems.get(position).getTitle());
        holder.artycleCity.setText(adapterItems.get(position).getCity());
        holder.artycleCountry.setText(adapterItems.get(position).getCountry());
        if (!adapterItems.get(position).getWallpaper_image().isEmpty()) {
            request.getArticleWallpaper((ImageView)holder.artycleImage, adapterItems.get(position).getWallpaper_image());
        } else {
            holder.artycleImage.setImageResource(R.mipmap.ic_launcher);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ArticleActivity.class);
                String res = "[{'title': '" + adapterItems.get(position).getTitle() + "','data':'" + adapterItems.get(position).getData()  +
                        "', 'wallpaper_image': '" + adapterItems.get(position).getWallpaper_image() + "','building_date':'" + adapterItems.get(position).getBuilding_date() +
                        "', 'city': '" + adapterItems.get(position).getCity() + "','country':'" + adapterItems.get(position).getCountry() +
                        "','latitude':'" + adapterItems.get(position).getLatitude() +  "','longitude':'" + adapterItems.get(position).getLongitude() +  "'}]";
                intent.putExtra("respons", res);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return adapterItems.size();
    }
}
