package com.arioki.thekos.conf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.arioki.thekos.R;
import java.util.ArrayList;

/**
 * Created by arioki on 11/18/2016.
 */

public class ImageItemAdapter extends ArrayAdapter<ImagesItem> {
    private ArrayList<ImagesItem> mListImages = new ArrayList<>();
    private Context mContext;
    private ImageLoader mImageLoader;

    public ImageItemAdapter(Context context) {
        super(context, R.layout.image_kos_list);
        mImageLoader = new ImageLoader(ApplicationClass.getInstance().getRequestQueue(),
        new BitmapLruCache());
    }

    public void setData(Context mContext, ArrayList<ImagesItem> listData){
        this.mContext = mContext;
        mListImages = listData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_kos_list,parent,false);

       }
        NetworkImageView imageView = (NetworkImageView)convertView.findViewById(R.id.imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);

        ImagesItem currentItem = getItem(position);

        imageView.setImageUrl(currentItem.getImageUrl(),mImageLoader);

        textView.setText(currentItem.getTitle());


       return convertView;
    }


    @Override
    public ImagesItem getItem(int position) {
        return mListImages.get(position);
    }

    @Override
    public int getCount() {
        return mListImages.size();
    }

    @Override
    public long getItemId(int position) {
        return mListImages.indexOf(getItem(position));
    }
}
