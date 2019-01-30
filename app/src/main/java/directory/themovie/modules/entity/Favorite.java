package directory.themovie.modules.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import directory.themovie.db.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static directory.themovie.db.DatabaseContract.getColumnInt;
import static directory.themovie.db.DatabaseContract.getColumnString;

public class Favorite implements Parcelable {
    private int id;
    private String original_title;
    private String poster_path;
    private String release_date;
    private String overview;

    public Favorite() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
    }

    public Favorite(Cursor listFavorite) {
        this.id = getColumnInt(listFavorite, _ID);
        this.original_title = getColumnString(listFavorite, DatabaseContract.FavoriteColumns.ORIGINAL_TITLE);
        this.release_date = getColumnString(listFavorite, DatabaseContract.FavoriteColumns.RELEASE_DATE);
        this.overview = getColumnString(listFavorite, DatabaseContract.FavoriteColumns.OVERVIEW);
        this.poster_path = getColumnString(listFavorite, DatabaseContract.FavoriteColumns.POSTER_PATH);
    }

    public Favorite(Parcel in) {
        this.id = in.readInt();
        this.original_title = in.readString();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
