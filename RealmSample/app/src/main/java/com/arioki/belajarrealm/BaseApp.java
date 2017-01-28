package com.arioki.belajarrealm;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Realm-1 Created by arioki on 11/01/2017.
 */
public class BaseApp extends Application {
    private static BaseApp sInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * konfigurasi volley
         */
        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;

        /**
         * konfigurasi realm
         */
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                //versi databse
                .schemaVersion(0)
                .migration(new DataMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private class DataMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            /**
             * mengambil schema
             */
            RealmSchema schema = realm.getSchema();

            /**
             * mengambil schema baru jika versi 0
             */
            if (oldVersion == 0) {
                schema.create("Article")
                        .addField("id", int.class)
                        .addField("title", String.class)
                        .addField("desription", String.class);
                oldVersion++;
            }
        }


    }

    /**
     * Kofigurasi Volley
     */

    public synchronized static BaseApp getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void addToRequestQueue(Request request) {
        mRequestQueue.add(request);
    }

}
