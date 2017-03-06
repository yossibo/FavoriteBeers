package frag.my.com.favoritebeers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

public class MainActivity extends AppCompatActivity {
    AQuery aq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(this);

        final SearchFrag searchFrag= new SearchFrag();

        getFragmentManager().beginTransaction().add(R.id.SearchFragCont, searchFrag).commit();


        aq.id(R.id.searchBtn).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String beerToSearch= aq.id(R.id.beerNameET).getText().toString();
                searchFrag.searchOnInternrt(beerToSearch);

            }
        });

        aq.id(R.id.allBeersAct).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, ListAllBeersAct.class);
                startActivity(intent);

            }
        });


    }
}
