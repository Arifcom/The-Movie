package directory.themoviefavorite.modules.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import directory.themoviefavorite.BuildConfig;
import directory.themoviefavorite.R;

import static directory.themoviefavorite.db.DatabaseContract.FavoriteColumns.ORIGINAL_TITLE;
import static directory.themoviefavorite.db.DatabaseContract.FavoriteColumns.POSTER_PATH;
import static directory.themoviefavorite.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static directory.themoviefavorite.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;
import static directory.themoviefavorite.db.DatabaseContract.getColumnString;

public class FavoriteAdapter extends CursorAdapter {
    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.favorite_item, viewGroup, false);
    }
    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            TextView original_title = (TextView)view.findViewById(R.id.original_title);
            TextView release_date = (TextView)view.findViewById(R.id.release_date);
            ImageView poster_path = (ImageView) view.findViewById(R.id.poster);
            original_title.setText(getColumnString(cursor,ORIGINAL_TITLE));
            release_date.setText(getColumnString(cursor,RELEASE_DATE));
            Picasso.with(context).load(BuildConfig.Image_TMDB + "w92" + getColumnString(cursor,POSTER_PATH)).into(poster_path);
        }
    }
}