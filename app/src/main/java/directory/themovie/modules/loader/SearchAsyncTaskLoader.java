package directory.themovie.modules.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import directory.themovie.modules.models.PopularModel;

public class SearchAsyncTaskLoader extends AsyncTaskLoader<ArrayList<PopularModel>> {
    private ArrayList<PopularModel> data;
    private boolean is_has_result = false;
    public String query;
    public SearchAsyncTaskLoader(final Context context, String query) {
        super(context);
        onContentChanged();
        this.query = query;
    }
    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (is_has_result)
            deliverResult(data);
    }
    @Override
    public void deliverResult(final ArrayList<PopularModel> data) {
        this.data = data;
        is_has_result = true;
        super.deliverResult(data);
    }
    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (is_has_result) {
            onReleaseResources(data);
            data = null;
            is_has_result = false;
        }
    }
    private static final String API_KEY = "1aed4b3170533f981cf14e6acac87567";
    @Override
    public ArrayList<PopularModel> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<PopularModel> popular_datas = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + query;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");
                    for (int i = 0 ; i < results.length() ; i++){
                        JSONObject popular = results.getJSONObject(i);
                        PopularModel popular_data = new PopularModel(popular);
                        popular_datas.add(popular_data);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(), "Request API failed", Toast.LENGTH_LONG ).show();
            }
        });
        return popular_datas;
    }
    protected void onReleaseResources(ArrayList<PopularModel> data) {

    }
}