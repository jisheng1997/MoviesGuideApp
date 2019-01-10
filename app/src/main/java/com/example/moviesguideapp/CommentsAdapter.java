package com.example.moviesguideapp;

import android.content.Context;
import java.util.List;

public class CommentsAdapter extends CommonRecycleAdapter<Comment> {
    private CommonViewHolder.onItemCommonClickListener commonClickListener;

    public CommentsAdapter(Context context, List<Comment> dataList) {
        super(context, dataList, R.layout.item_comments);
    }

    public CommentsAdapter(Context context, List<Comment> dataList, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, R.layout.item_comments);
        this.commonClickListener = commonClickListener;
    }

    @Override
    void bindData(CommonViewHolder holder, Comment comment) {
        holder.setText(R.id.movies_comment_username,comment.getUserName());
        holder.setText(R.id.movies_comment,comment.getComment());
        holder.setCommonClickListener(commonClickListener);
    }
}