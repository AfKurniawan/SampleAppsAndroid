package com.arioki.belajarrealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private RecyclerView recyclerView;
    private RealmHelper helper;
    private ArrayList<ArticleModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        helper = new RealmHelper(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.rvArticle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), tambah.class));
                finish();
            }
        });

        setRecylerView();
    }

    /**
     * set recyclerView
     * ambil data dari relm
     */
    private void setRecylerView() {
        try {
            data = helper.findAllArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdapterArticle adapter = new AdapterArticle(data, new AdapterArticle.OnItemClickListener() {
            @Override
            public void onClick(ArticleModel item) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("titile", item.getTitle());
                i.putExtra("description", item.getDescription());
                startActivity(i);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            data = helper.findAllArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //data = helper.findAllArticle();
        setRecylerView();
    }
}
