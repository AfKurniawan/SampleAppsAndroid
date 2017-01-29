package com.arioki.thekos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arioki.thekos.conf.ImagesItem;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailKos extends AppCompatActivity {
    public static String id, nama, harga, alamat;
    private TextView kosname, kosadress;
    private ImagesItem item;
    private static final String TAG = "ListDislay";
    public static String KEY_ITEM = "item";
    SliderLayout mDemoSlider;
    int numData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setTitle(nama.toString());
        item = (ImagesItem) getIntent().getSerializableExtra(KEY_ITEM);
        kosname = (TextView) findViewById(R.id.studioname);
        kosadress = (TextView) findViewById(R.id.studio_address);
        kosname.setText(nama);
        kosadress.setText(alamat);
        if (item != null) {
            kosname.setText(item.getTitle().toString());
            // getSupportActionBar().setTitle(item.getTitle().toString());
            Log.d("Key_item", item.getTitle().toString());
        }
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        getGambar();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else if (getFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    super.onBackPressed();
                } else if (getFragmentManager().getBackStackEntryCount() == 1) {
                    moveTaskToBack(false);
                } else {
                    getFragmentManager().popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getGambar() {
        String url = "http://cloudofoasis.com/api/Ivan/getGambar.php?StudioMusik=" + id;
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        numData = response.length();
                        if (numData == 0) mDemoSlider.setVisibility(View.GONE);

                        else {
                            String[] gambar_kos = new String[numData],
                                    nama_kos = new String[numData];
                            HashMap<String, String> url_maps = new HashMap<String, String>();
                            for (int i = 0; i < numData; i++) {
                                try {
                                    JSONObject item = response.getJSONObject(i);
                                    gambar_kos[i] = item.getString("gambar");
                                    nama_kos[i] = item.getString("nama");
                                    url_maps.put(nama_kos[i], "http://cloudofoasis.com/api/Ivan/slider_studio/" + gambar_kos[i]);
                                    Log.d("Slide_kos", url_maps.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("Response", e.toString());
                                    getGambar();
                                }
                            }
                            for (String name : url_maps.keySet()) {
                                TextSliderView textSliderView = new TextSliderView(DetailKos.this);
                                textSliderView.description(name).image(url_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit);
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle().putString("extra", name);
                                mDemoSlider.addSlider(textSliderView);
                                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
                                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                                mDemoSlider.setDuration(4000);
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("Response", error.toString());
                        getGambar();
                    }
                });
        Volley.newRequestQueue(this).add(request);
    }
}
