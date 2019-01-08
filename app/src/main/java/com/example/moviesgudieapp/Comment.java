package com.example.moviesgudieapp;

public class Comment {
    private String username;
    private String comment;

    boolean comment_limit = false;

    public Comment(String username, String comment) {
        this.username = username;
        if (comment.length() <= 45) {
            comment_limit = true;
            this.comment = comment;
        }
    }

    public boolean getComment_limit() { return comment_limit;}

    public String getUserName() { return username; }

    public String getComment() { return comment; }




}
