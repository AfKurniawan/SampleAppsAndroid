package com.arioki.belajarrealm;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Realm-1 Created by arioki on 11/01/2017.
 */
public class RealmHelper {

    private static final String TAG = "RealmHelper";

    private Realm realm;
    private RealmResults<Article> realmResult;
    public Context context;

    /**
     * constructor untuk membuat instance realm
     *
     * @param context
     */
    public RealmHelper(Context context) {
        realm = Realm.getInstance(context);
        this.context = context;
    }

    /**
     * menambah data pada database realm
     *
     * @param title
     * @param description
     */
    public void addArticle(String title, String description) {

        Article article = new Article();
        article.setId((int) (System.currentTimeMillis() / 1000));
        article.setTitle(title);
        article.setDescription(description);

        realm.beginTransaction();
        realm.copyToRealm(article);
        realm.commitTransaction();
        showLog("Added ; " + title);
        showToast("Data Berasil di tambahkan");
    }

    /**
     * method mencari semua article
     *
     * @return
     */

    public ArrayList<ArticleModel> findAllArticle() {
        ArrayList<ArticleModel> data = new ArrayList<>();

        realmResult = realm.where(Article.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            showLog("Size : " + realmResult.size());

            for (int i = 0; i < realmResult.size(); i++) {
                String title, description;
                int id = realmResult.get(i).getId();
                title = realmResult.get(i).getTitle();
                description = realmResult.get(i).getDescription();
                data.add(new ArticleModel(id, title, description));
            }
        } else {
            showLog("Size : 0");
            showToast("Database Kosong !");
        }

        return data;
    }

    /**
     * method update artile
     *
     * @param id
     * @param title
     * @param description
     */
    public void updateArticle(int id, String title, String description) {
        realm.beginTransaction();
        Article article = realm.where(Article.class).equalTo("id", id).findFirst();
        article.setTitle(title);
        article.setDescription(description);
        realm.commitTransaction();
        showLog("Update :" + title);

        showToast(title + " berhasil di update");
    }

    /**
     * method untuk menghapus berdasarkan if
     *
     * @param id
     */
    public void deleteData(int id) {
        RealmResults<Article> dataResults = realm.where(Article.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataResults.remove(0);
        dataResults.removeLast();
        dataResults.clear();
        realm.commitTransaction();

        showToast("Hapus data berhasil. ");
    }



    /**
     * Membuat method Toast
     *
     * @param s
     */
    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Membuat method Log.d
     *
     * @param s
     */
    private void showLog(String s) {
        Log.d("Log APP", s);
    }
}
