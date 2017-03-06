package frag.my.com.favoritebeers;

import com.orm.SugarRecord;

/**
 * Created by jbt on 06/03/2017.
 */

public class Beer extends SugarRecord {

    String name;
    String image_url;

    public Beer(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }


    public Beer( ) {

    }
    @Override
    public String toString() {
        return name;

    }
}


