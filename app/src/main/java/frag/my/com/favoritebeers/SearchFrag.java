package frag.my.com.favoritebeers;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarContext;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFrag extends Fragment {
    ListView lv;
    List<Beer> allBeers;



    public SearchFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SugarContext.init(getActivity());

        View view= inflater.inflate(R.layout.fragment_search, container, false);


        lv= (ListView) view.findViewById(R.id.beerLV);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Beer  CurrentBeer=  allBeers.get(position);
                CurrentBeer.save();
                Toast.makeText(getActivity(), "saved!!", Toast.LENGTH_SHORT).show();



            }
        });

        return view;
    }


    public void searchOnInternrt(String beerName)
    {
//start new asynctask
        Downloader downloader= new Downloader();
        downloader.execute("https://api.punkapi.com/v2/beers?beer_name="+beerName);



    }






    public class Downloader extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {

            String urlToSearch= params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlToSearch)
                    .build();

            String result="";


            try {
                Response response  = client.newCall(request).execute();
                result= response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }


        @Override
        protected void onPostExecute(String resultJson) {

            Log.d("JSON", resultJson);
            Gson gson= new Gson();
            Type listType = new TypeToken<List<Beer>>(){}.getType();
            allBeers = gson.fromJson(resultJson , listType);

            BeerAdapter arrayAdapter= new BeerAdapter(getActivity(), R.layout.beer_item, allBeers);
            lv.setAdapter(arrayAdapter);

        }
    }























}
