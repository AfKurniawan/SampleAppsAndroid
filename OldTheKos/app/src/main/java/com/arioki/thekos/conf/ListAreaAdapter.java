package com.arioki.thekos.conf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arioki.thekos.R;

import java.util.ArrayList;

/**
 * Created by arioki on 07/12/2016.
 */

public class ListAreaAdapter extends ArrayAdapter<ListAreaItem> {
    private ArrayList<ListAreaItem>  mLisAreaItem = new ArrayList<>();
    private Context mContext;
    public ListAreaAdapter(Context context) {
        super(context, R.layout.home_tab_2_isi);

    }

    public void setData(Context mContext, ArrayList<ListAreaItem> listData){
        this.mContext = mContext;
        mLisAreaItem = listData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_tab_2_isi,parent,false);
       }
        TextView textNama =(TextView)convertView.findViewById(R.id.tab2_nama);

        ListAreaItem currentItem = getItem(position);
        textNama.setText(currentItem.getAreaNama());

        return convertView;
    }

    @Override
    public ListAreaItem getItem(int position) {
        return mLisAreaItem.get(position);
    }

    @Override
    public int getCount() {
        return mLisAreaItem.size();
    }

    @Override
    public long getItemId(int position) {
        return mLisAreaItem.indexOf(getItem(position));
    }
}

