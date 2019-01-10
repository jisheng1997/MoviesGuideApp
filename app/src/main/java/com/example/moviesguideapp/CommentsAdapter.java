package com.example.moviesguideapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{
    private List<Comment> mCommentList;
    private Activity mActivity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View commentView;
        TextView userName;
        TextView content;

        public  ViewHolder(View view){
            super(view);
            commentView = view;
            userName = (TextView)view.findViewById(R.id.movies_comment_username);
            content = (TextView) view.findViewById(R.id.movies_comment);
        }
    }

    public CommentsAdapter(Activity activity, List<Comment> CommentList){
        mActivity = activity;
        mCommentList = CommentList;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments,parent,false);
        final CommentsAdapter.ViewHolder holder = new CommentsAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position){
        Comment comment = mCommentList.get(position);
        holder.userName.setText(comment.getUserName());
        holder.content.setText(comment.getComment());
    }

    @Override
    public int getItemCount(){
        return mCommentList.size();
    }

}

