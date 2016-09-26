package com.valarhao.valarnews.module.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.valarhao.valarnews.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

    private List<NewsItem> mNewsItems;
    private LayoutInflater mInflater;

    public RecyclerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mNewsItems = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem) {
        if (!mNewsItems.contains(newsItem)) {
            mNewsItems.add(newsItem);
        }
    }

    public NewsItem getNewsItem(int position) {
        return mNewsItems.get(position);
    }

    public void clear() {
        mNewsItems.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_recycler_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem newsItem = mNewsItems.get(position);
        holder.itemTxt.setText(newsItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView itemCard;
        public ImageView itemImg;
        public TextView itemTxt;

        public ViewHolder(View view) {
            super(view);
            itemCard = (CardView) view.findViewById(R.id.card_item_list);
            itemImg = (ImageView) view.findViewById(R.id.img_item_list);
            itemTxt = (TextView) view.findViewById(R.id.txt_item_list);
        }
    }
}
