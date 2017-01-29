package com.arioki.thekos;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.os.Handler;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arioki.thekos.conf.ImageItemAdapter;
import com.arioki.thekos.conf.ImagesItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import static com.arioki.thekos.helper.Http.server;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListKos extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private String link = server;
    private ImageItemAdapter mAdapter;
    private ListView listView;
    public static String KEY_ITEM = "item";
    private ArrayList<ImagesItem> mList;
    private SwipeRefreshLayout swipe;
    private int offset = 0;
    int no;
    Runnable runnable;

    public ListKos() {
        // Required empty public constructor
    }

    View listKosView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listKosView = inflater.inflate(R.layout.list_kos, container, false);

        mList = new ArrayList<>();
        mAdapter = new ImageItemAdapter(getActivity().getApplicationContext());
        listView = (ListView) listKosView.findViewById(R.id.listView);
        swipe = (SwipeRefreshLayout) listKosView.findViewById(R.id.list_SwipeRefresh);
        mList.clear();
        listView.setAdapter(mAdapter);
        getJsonApi(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImagesItem kos = (ImagesItem) mList.get(position);

                Intent intent = new Intent(getActivity(), DetailKos.class);
                intent.putExtra(DetailKos.KEY_ITEM, kos);
                startActivityForResult(intent, 0);
            }
        });

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                mList.clear();
                mAdapter.notifyDataSetChanged();
                getJsonApi(0);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    swipe.setRefreshing(true);
                    Handler handler = new Handler();

                    runnable = new Runnable() {
                        public void run() {
                            getJsonApi(offset);
                        }
                    };
                    handler.postDelayed(runnable, 0);
                }
            }
        });
        return listKosView;
    }

    @Override
    public void onRefresh() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        getJsonApi(0);
    }

    private void getJsonApi(int page) {
        swipe.setRefreshing(true);
        Log.d("lokasi", String.valueOf(page));
        JsonArrayRequest request = new JsonArrayRequest(link + "lokasi/" + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //parsing json
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject item = response.getJSONObject(i);
                                String title = item.getString("title");
                                String imageUrl = item.getString("imgurl");
                                no = item.getInt("no");
                                ImagesItem imagesItem = new ImagesItem(title, link + imageUrl);
                                mList.add(imagesItem);

                                if (no > offset) offset = no;

                                Log.d("respon", item + imageUrl + title.toString());


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
