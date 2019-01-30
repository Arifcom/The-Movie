package directory.themovie.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import directory.themovie.R;
import directory.themovie.entity.Favorite;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewholder> {
    private Cursor listFavorite;
    private Activity activity;
    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }
    public void setListNotes(Cursor listNotes) {
        this.listFavorite = listNotes;
    }
    @Override
    public FavoriteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new FavoriteViewholder(view);
    }
    @Override
    public void onBindViewHolder(FavoriteViewholder holder, int position) {
        final Favorite favorite = getItem(position);
        holder.originalTitle.setText(favorite.getOriginal_title());
        holder.releaseDate.setText(favorite.getRelease_date());
    }
    @Override
    public int getItemCount() {
        if (listFavorite == null) return 0;
        return listFavorite.getCount();
    }
    private Favorite getItem(int position) {
        if (!listFavorite.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Favorite(listFavorite);
    }
    class FavoriteViewholder extends RecyclerView.ViewHolder {
        TextView originalTitle, releaseDate;
        CardView cvFavorite;
        FavoriteViewholder(View itemView) {
            super(itemView);
            originalTitle = (TextView) itemView.findViewById(R.id.original_title);
            releaseDate = (TextView) itemView.findViewById(R.id.release_date);
            cvFavorite = (CardView) itemView.findViewById(R.id.cv_favorite_item);
        }
    }
}
