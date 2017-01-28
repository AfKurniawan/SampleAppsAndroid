package com.arioki.listjualmobil;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;




import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    String[][] data = new String[][]{
            {"Honda, Brio, 1.2L CKD S A/T, 2015",
                    "http://otoboy.com/wp-content/uploads/2015/01/Spesifikasi-dan-Harga-Honda-Brio.jpg","19 Juta", "Jakarta Selatan"},
            {"Honda, Jazz, RS 1.5 A/T, 2015",
                    "http://image.priceprice.k-img.com/global/images/product/cars/Honda_Jazz/3/Honda_Jazz_hatchback_L_1.jpg","19 Juta", "Jakarta Selatan"},
            {"Mazda, Mazda2, V 1.5 M/T, 2014",
                    "http://image.priceprice.k-img.com/global/images/product/cars/Mitsubishi_Pajero_Sport/3/Mitsubishi_Pajero_Sport_wagon_L_1.jpg","Rp. 223.100.000", "Tangerang"},
            {"Honda, Jazz, A 1.5 M/T, 2015",
                    "http://image.priceprice.k-img.com/global/images/product/cars/Honda_Jazz/3/Honda_Jazz_hatchback_L_1.jpg","Rp. 207.500.000", "Jakarta Barat"},
            {"Mitsubishi, Outlander, Sport PX, 2015",
                    "http://image.priceprice.k-img.com/global/images/product/cars/Mitsubishi_Pajero_Sport/3/Mitsubishi_Pajero_Sport_wagon_L_1.jpg","Call", "Tangerang"}

    };

   private ListView lvItem;
    private ArrayList listItem;

    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.home_title);

        lvItem = (ListView) findViewById(R.id.lv_item);
        listItem = new ArrayList<>();

        MobilModel mobil = null;


        for (int i =0; i < data.length; i++){
            mobil = new MobilModel();
            mobil.setTitle(data[i][0]);
            mobil.setImage(data[i][1]);
            mobil.setHarga(data[i][2]);
            mobil.setLokasi(data[i][3]);

            listItem.add(mobil);
        }


        MobilAdapter adapter = new MobilAdapter(MainActivity.this, listItem);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                MobilModel mbl = (MobilModel) listItem.get(position);
                Intent intent = new Intent(MainActivity.this, DetailMobilActivity.class);
                intent.putExtra(DetailMobilActivity.KEY_ITEM, mbl);

                startActivityForResult(intent, 0);
            }
        });


    }


}
