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
import com.khachik.explore.utils.AddToTheSharedPrefs;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.khachik.explore.R.id.fab;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private AddToTheSharedPrefs addToTheSharedPrefs;
    private Requests request;
    private Context context;
    private ArrayList<ArticlesModel> adapterItems;
    private  ArrayList<String> favoritesList;




    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        private final TextView artycleTitle;
        private final TextView artycleCity;
        private final TextView artycleCountry;
        private ImageView favorite;
        private final CircleImageView artycleImage;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            artycleTitle = (TextView) view.findViewById(R.id.artycle_title);
            artycleCity = (TextView) view.findViewById(R.id.artycle_city);
            artycleCountry = (TextView) view.findViewById(R.id.artycle_country);
            artycleImage = (CircleImageView) view.findViewById(R.id.artycle_image);
            favorite = (ImageView) view.findViewById(R.id.favorite);
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        this.addToTheSharedPrefs = AddToTheSharedPrefs.getInstance();
        this.favoritesList =  this.addToTheSharedPrefs.getFavorites(context);

        holder.artycleTitle.setText(adapterItems.get(position).getTitle());
        holder.artycleCity.setText(adapterItems.get(position).getCity());
        holder.artycleCountry.setText(adapterItems.get(position).getCountry());
        if (!adapterItems.get(position).getWallpaper_image().isEmpty()) {
            request.getArticleWallpaper((ImageView)holder.artycleImage, adapterItems.get(position).getWallpaper_image());
        } else {
            holder.artycleImage.setImageResource(R.mipmap.ic_launcher);
        }
        final String id = adapterItems.get(position).getId();
        setFavButtonBackground(holder.favorite, id);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favoritesList.contains(id)) {
                    addToTheSharedPrefs.removeFavorite(context, id);
                    favoritesList.remove(id);
                    holder.favorite.setImageResource(R.drawable.ic_heart);
                } else {
                    addToTheSharedPrefs.addFavorite(context, id);
                    favoritesList.add(id);
                    holder.favorite.setImageResource(R.drawable.ic_fill_heart);
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ArticleActivity.class);
                String res = "[{" + "'id': '" + adapterItems.get(position).getId() +
                        "','title': '" + adapterItems.get(position).getTitle() +
                        "','data':'" + adapterItems.get(position).getData()  +
                        "', 'wallpaper_image': '" + adapterItems.get(position).getWallpaper_image() +
                        "', 'images_folder': '" + adapterItems.get(position).getImages_folder() +
                        "','building_date':'" + adapterItems.get(position).getBuilding_date() +
                        "', 'city': '" + adapterItems.get(position).getCity() +
                        "','country':'" + adapterItems.get(position).getCountry() +
                        "','latitude':'" + adapterItems.get(position).getLatitude() +
                        "','longitude':'" + adapterItems.get(position).getLongitude() +  "'}]";
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

    private void setFavButtonBackground(ImageView favorit, String id) {
        System.out.println("FAB - " + id);
        if(this.favoritesList.contains(id)) {
            favorit.setImageResource(R.drawable.ic_fill_heart);
        }
    }

    public void callOnCreate(){
        System.out.println("Caaled");
    }
}
