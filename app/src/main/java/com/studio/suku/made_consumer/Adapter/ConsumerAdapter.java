package com.studio.suku.made_consumer.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio.suku.made_consumer.LocalDb.FavoriteItem;
import com.studio.suku.made_consumer.R;

import java.util.ArrayList;

public class ConsumerAdapter extends RecyclerView.Adapter<ConsumerAdapter.FavoriteHolder> {

    private final ArrayList<FavoriteItem> listFavorites = new ArrayList<>();
    private final Activity activity;

    public ConsumerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListFavorites(ArrayList<FavoriteItem> list){
        this.listFavorites.clear();
        this.listFavorites.addAll(list);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favorite, viewGroup, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder favoriteHolder, int i) {
        favoriteHolder.judul.setText(listFavorites.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listFavorites.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView judul;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.title_favorite);
        }
    }
}
