package frag.my.com.favoritebeers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.List;

public class ListAllBeersAct extends AppCompatActivity {



    List<Beer> allBeers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_beers);


        SugarContext.init(this);

        ListView lv= (ListView)findViewById(R.id.allBeersLV);

         allBeers = Beer.listAll(Beer.class);
        BeerAdapter arrayAdapter= new BeerAdapter(this, R.layout.beer_item, allBeers);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beer c= allBeers.get(position);
                Toast.makeText(ListAllBeersAct.this,  c.name, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
