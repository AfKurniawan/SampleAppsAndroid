package com.arioki.belajarrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private RealmHelper helper;
    private int position;
    private Button delete, save;
    private EditText inputTitle, inputDescription;
    private String title, description;
    private String intentTitle, intentDescription;
    private ArrayList<ArticleModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        helper = new RealmHelper(EditActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);
        intentTitle = getIntent().getStringExtra("Title");
        intentDescription = getIntent().getStringExtra("description");

        delete = (Button)findViewById(R.id.delete);
        save = (Button)findViewById(R.id.save);

        inputTitle = (EditText)findViewById(R.id.inputTitle);
        inputDescription = (EditText)findViewById(R.id.inputDescription);

        inputTitle.setText(intentTitle);
        inputDescription.setText(intentDescription);

        /**
         * Perintah Hapus
         */

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * perintah untuk menghapus
                 */
                helper.deleteData(position);

                /**
                 * pindah ke mainActivity
                 */
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });

        /**
         * perintah update data
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * mengambil data data dari edit text
                 */
                title = inputTitle.getText().toString();
                description = inputDescription.getText().toString();

                /**
                 * melakukan update artikel
                 */
                helper.updateArticle(position, title, description);

                /**
                 * kembali ke mainActivity
                 */
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
