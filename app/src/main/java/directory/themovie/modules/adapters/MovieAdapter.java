package directory.themovie.modules.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.modules.models.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<MovieModel> list_movie;

    private ArrayList<MovieModel> getListMovie() {
        return list_movie;
    }

    public void setListMovie(ArrayList<MovieModel> list_movie) {
        this.list_movie = list_movie;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.textViewOriginalTitle.setText(getListMovie().get(position).getOriginal_title());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w92" + getListMovie().get(position).getPoster_path()).into(holder.imageViewPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOriginalTitle;
        ImageView imageViewPoster;
        GridViewHolder(View itemView) {
            super(itemView);
            textViewOriginalTitle = itemView.findViewById(R.id.original_title);
            imageViewPoster = itemView.findViewById(R.id.poster);
        }
    }
}
