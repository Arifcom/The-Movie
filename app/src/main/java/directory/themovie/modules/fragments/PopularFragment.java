package directory.themovie.modules.fragments;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.activity.MyAsyncTaskLoader;
import directory.themovie.modules.adapters.WeatherAdapter;
import directory.themovie.modules.models.WeatherItems;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>> {

    ListView listView;
    WeatherAdapter adapter;
    EditText editKota;
    Button btnSearch;

    static final String EXTRAS_CITY = "EXTRAS_CITY";


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.popular_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new WeatherAdapter(getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();
        listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        editKota = (EditText) view.findViewById(R.id.edit_kota);
        btnSearch = (Button) view.findViewById(R.id.btn_kota);

        btnSearch.setOnClickListener(myListener);

        String kota = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_CITY, kota);

        //Inisiasi dari Loader, dimasukkan ke dalam onCreate
        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanKota = "";
        if (args != null){
            kumpulanKota = args.getString(EXTRAS_CITY);
        }

        return new MyAsyncTaskLoader(getActivity().getApplicationContext(),kumpulanKota);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<WeatherItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = editKota.getText().toString();

            if (TextUtils.isEmpty(kota))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_CITY,kota);
            getLoaderManager().restartLoader(0,bundle, PopularFragment.this);
        }
    };

    @Override
    public void onClick(View view) {

    }
}
