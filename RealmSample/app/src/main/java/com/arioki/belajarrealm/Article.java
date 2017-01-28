package com.arioki.belajarrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Realm-1 Created by arioki on 11/01/2017.
 */
public class Article extends RealmObject {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrimaryKey
    private  int id;
    private String title;
    private String description;
}
