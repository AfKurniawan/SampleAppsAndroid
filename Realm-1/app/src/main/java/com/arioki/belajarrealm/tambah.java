package com.arioki.belajarrealm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah extends AppCompatActivity {
    public Context context;
    private RealmHelper realmHelper;
    private EditText inputDescription;
    private EditText inputTitle;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        realmHelper = new RealmHelper(tambah.this);

        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputDescription = (EditText) findViewById(R.id.inputDescription);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi string
                String title, description;

                //mengambil text dari edit text
                title = inputTitle.getText().toString();
                description = inputDescription.getText().toString();


                try {
                    //menambah data ke databse
                    realmHelper.addArticle(title, description);
                    //menutup Add Activity

                    finish();
                    //kembali ke Main Activity

                    Intent i = new Intent(tambah.this, MainActivity.class);
                    startActivity(i);
                } catch (Exception e) {
                }


            }
        });

    }
}

