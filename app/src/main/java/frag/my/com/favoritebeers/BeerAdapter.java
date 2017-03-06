package frag.my.com.favoritebeers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jbt on 06/03/2017.
 */

public class BeerAdapter extends ArrayAdapter<Beer> {

    Context context;
    int resource;
    List<Beer> allBeers;



    public BeerAdapter(Context context, int resource, List<Beer> allBeers) {
        super(context, resource, allBeers);

        this.allBeers=allBeers;
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= LayoutInflater.from(context).inflate(resource, null);

        TextView beernameTV= (TextView) view.findViewById(R.id.beername);

        ImageView beerImage= (ImageView) view.findViewById(R.id.beerImage);


        Beer currentBeer= allBeers.get(position);

        beernameTV.setText(currentBeer.name);

        Picasso.with(context).load(currentBeer.image_url).into(beerImage);

        return view;
    }
}
