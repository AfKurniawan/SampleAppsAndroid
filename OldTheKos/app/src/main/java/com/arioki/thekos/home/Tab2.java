package com.arioki.thekos.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arioki.thekos.R;
import com.arioki.thekos.conf.ListAreaAdapter;
import com.arioki.thekos.conf.ListAreaItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static com.arioki.thekos.helper.Http.server;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private String link = server;
    private ListAreaAdapter mAdapter;
    private ListView listView;
    private ArrayList<ListAreaItem> mList;
    private SwipeRefreshLayout swipe;

    public Tab2() {
        // Required empty public constructor
    }

    View tab2View;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tab2View = inflater.inflate(R.layout.home_tab2, container, false);

        mList = new ArrayList<>();
        mAdapter = new ListAreaAdapter(getActivity().getApplicationContext());
        listView = (ListView) tab2View.findViewById(R.id.listAreaView);
        swipe = (SwipeRefreshLayout) tab2View.findViewById(R.id.tab2_swipe);
        mList.clear();
        listView.setAdapter(mAdapter);
        getJsonApi();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListAreaItem area = (ListAreaItem) mList.get(position);

                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");

            }
        });

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                mList.clear();
                mAdapter.notifyDataSetChanged();
                getJsonApi();
            }
        });
        return tab2View;
    }

    @Override
    public void onRefresh() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        getJsonApi();
    }

    private void getJsonApi() {
        swipe.setRefreshing(true);
        JsonArrayRequest request = new JsonArrayRequest(link + "list_area",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //parsing json
                        Log.d("Jumlah_lokasi", String.valueOf(response.length()));
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject item = response.getJSONObject(i);
                                String id = item.getString("id");
                                String lokasi = item.getString("lokasi");
                                String icon = item.getString("icon");
                                String jumlah = item.getString("jumlah");

                                ListAreaItem listAreaItem = new ListAreaItem(id, lokasi, link + icon);
                                mList.add(listAreaItem);
                                Log.d("respon_area", item.toString());


                            }

                            mAdapter.setData(getActivity().getApplicationContext(), mList);
                            mAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                        }
                        swipe.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipe.setRefreshing(false);
            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
    }
}
