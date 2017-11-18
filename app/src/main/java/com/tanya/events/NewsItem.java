package com.tanya.events;

import java.io.Serializable;

/**
 * Created by Tanya on 11/18/2017.
 */

public class NewsItem implements Serializable{

    String title ;
    String link ;
    String imagePath ;
    String description;
    String date ;


    @Override
    public String toString() {
        return title;
    }
}
