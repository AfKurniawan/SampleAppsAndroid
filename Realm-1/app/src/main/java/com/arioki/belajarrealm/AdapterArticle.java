package com.arioki.belajarrealm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import com.arioki.belajarrealm.R;
import com.arioki.belajarrealm.ArticleModel;

/**
 * Realm-1 Created by arioki on 12/01/2017.
 */
public class AdapterArticle extends RecyclerView.Adapter<AdapterArticle.ViewHolder> {
    private ArrayList<ArticleModel> article;
    private final OnItemClickListener listener;

    public AdapterArticle(ArrayList<ArticleModel> article, OnItemClickListener listener) {
        this.article = article;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(ArticleModel item);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.click(article.get(position), listener);
        holder.tvId.setText(String.valueOf(article.get(position).getId()));
        holder.title.setText(article.get(position).getTitle());
        holder.description.setText(article.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return article.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, title, description;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
        }

        public void click(final ArticleModel articleModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(articleModel);
                }
            });
        }

    }

    @Override
    public AdapterArticle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


}
