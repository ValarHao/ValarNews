package com.valarhao.valarnews.module.zhihu.common.detail.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.GlideUtil;
import com.valarhao.valarnews.common.util.TimeUtil;
import com.valarhao.valarnews.common.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<DetailShortJson.Comment> mComments;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecyclerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mComments = new ArrayList<>();
    }

    public void addRecyclerItem(DetailShortJson.Comment comment) {
        if (!mComments.contains(comment)) {
            mComments.add(comment);
        }
    }

    public DetailShortJson.Comment getRecyclerItem(int position) {
        return mComments.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.zhihu_item_detail_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailShortJson.Comment comment = mComments.get(position);
        GlideUtil.load(mContext, comment.getAvatar(), holder.imgItemFace);
        holder.txtItemName.setText(comment.getAuthor());
        holder.txtItemContent.setText(comment.getContent());
        holder.txtItemTime.setText(TimeUtil.format(comment.getTime()));
        holder.txtItemLike.setText(comment.getLikes() + "");
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView imgItemFace;
        public TextView txtItemName;
        public TextView txtItemContent;
        public TextView txtItemTime;
        public TextView txtItemLike;

        public ViewHolder(View view) {
            super(view);
            imgItemFace = (CircleImageView) view.findViewById(R.id.imgZhihuCommentFace);
            txtItemName = (TextView) view.findViewById(R.id.txtZhihuCommentName);
            txtItemContent = (TextView) view.findViewById(R.id.txtZhihuCommentContent);
            txtItemTime = (TextView) view.findViewById(R.id.txtZhihuCommentTime);
            txtItemLike = (TextView) view.findViewById(R.id.txtZhihuCommentLike);
        }
    }
}
