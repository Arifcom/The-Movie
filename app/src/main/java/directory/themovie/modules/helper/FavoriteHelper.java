package directory.themovie.modules.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import directory.themovie.db.DatabaseHelper;
import directory.themovie.modules.entity.Favorite;

import static android.provider.BaseColumns._ID;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.TABLE_NAME;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.ORIGINAL_TITLE;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;

public class FavoriteHelper {
    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    public FavoriteHelper(Context context) {
        this.context = context;
    }
    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dataBaseHelper.close();
    }
    public ArrayList<Favorite> query() {
        ArrayList<Favorite> arrayList = new ArrayList<Favorite>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        Favorite favorite;
        if (cursor.getCount() > 0) {
            do {
                favorite = new Favorite();
                favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favorite.setOriginal_title(cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_TITLE)));
                favorite.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                arrayList.add(favorite);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public long insert(Favorite favorite) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(ORIGINAL_TITLE, favorite.getOriginal_title());
        initialValues.put(RELEASE_DATE, favorite.getRelease_date());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }
    public int update(Favorite favorite) {
        ContentValues args = new ContentValues();
        args.put(ORIGINAL_TITLE, favorite.getOriginal_title());
        args.put(RELEASE_DATE, favorite.getRelease_date());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + favorite.getId() + "'", null);
    }
    public int delete(int id) {
        return database.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
    }
    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }
    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }
    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }
    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}


