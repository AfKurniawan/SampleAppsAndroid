package com.arioki.thekos.conf;

import java.io.Serializable;

/**
 * Created by arioki on 11/18/2016.
 */

public class ImagesItem implements Serializable{
    //Lis Kos
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    private String imageUrl;

    public ImagesItem(String title, String imageUrl){
      this.title = title;
        this.imageUrl = imageUrl;
    }

}
