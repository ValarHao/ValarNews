package com.valarhao.valarnews.module.zhihu.common;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTabAdapter extends RecyclerView.Adapter<RecyclerTabAdapter.ViewHolder> {

    private List<RecyclerItem> mRecyclerItems;
    private LayoutInflater mInflater;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerTabAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mRecyclerItems = new ArrayList<>();
    }

    public void addRecyclerItem(RecyclerItem recyclerItem) {
        if (!mRecyclerItems.contains(recyclerItem)) {
            mRecyclerItems.add(recyclerItem);
        }
    }

    public RecyclerItem getRecyclerItem(int position) {
        return mRecyclerItems.get(position);
    }

    public void clear() {
        mRecyclerItems.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.zhihu_item_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RecyclerItem recyclerItem = mRecyclerItems.get(position);
        holder.itemTxtTitle.setText(recyclerItem.getTitle());
        //加载图片
        if (recyclerItem.getImgLink() != null) {
            GlideUtil.load(mContext, recyclerItem.getImgLink(), holder.itemImg);
        }
        //点击item监听
        if (mOnItemClickListener != null) {
            //holder.itemCard.setBackgroundResource(R.drawable.ripple); //点击水波纹效果
            holder.itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemCard, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView itemCard;
        public ImageView itemImg;
        public TextView itemTxtTitle;

        public ViewHolder(View view) {
            super(view);
            itemCard = (CardView) view.findViewById(R.id.cardItemTab);
            itemImg = (ImageView) view.findViewById(R.id.imgItemTab);
            itemTxtTitle = (TextView) view.findViewById(R.id.txtItemTab);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
