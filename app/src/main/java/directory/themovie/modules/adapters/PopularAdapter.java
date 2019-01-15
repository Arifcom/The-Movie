package directory.themovie.modules.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.modules.models.PopularModel;

public class PopularAdapter extends BaseAdapter {
    private ArrayList<PopularModel> data = new ArrayList<>();
    private LayoutInflater object_inflater;
    private Context context;
    public PopularAdapter(Context context) {
        this.context = context;
        object_inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setData(ArrayList<PopularModel> items){
        data = items;
        notifyDataSetChanged();
    }
    public void addItem(final PopularModel item) {
        data.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        data.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }
    @Override
    public PopularModel getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = object_inflater.inflate(R.layout.popular_items, null);
            holder.textViewOriginalTitle = (TextView)convertView.findViewById(R.id.original_title);
            holder.imageViewPoster = (ImageView)convertView.findViewById(R.id.poster);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewOriginalTitle.setText(data.get(position).getOriginal_title());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w92" + data.get(position).getPoster_path()).into(holder.imageViewPoster);
        return convertView;
    }
    private static class ViewHolder {
        TextView textViewOriginalTitle;
        ImageView imageViewPoster;
    }
}
