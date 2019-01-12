/**
 * project name:doubanjiang
 * packageName:com.example.moviesguideapp
 * fileName:Comment
 * data:2019/1/10 15:19
 */

package com.example.moviesguideapp;

/**
 * The type Comment.
 */
public class Comment {

    private String username;
    private String comment;

    private boolean comment_limit = false;

    /**
     * Instantiates a new Comment.
     * @param username the username
     * @param comment  the comment
     */
    public Comment(String username, String comment) {
        this.username = username;
        if (comment.length() <= 45) {
            comment_limit = true;
            this.comment = comment;
        }
    }

    /**
     * Gets comment limit.
     * @return the comment limit
     */
    public boolean getComment_limit() { return comment_limit;}

    /**
     * Gets user name.
     * @return the user name
     */
    public String getUserName() { return username; }

    /**
     * Gets comment.
     * @return the comment
     */
    public String getComment() { return comment; }

    /**
     * Sets username.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets comment.
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Sets comment limit.
     * @param comment_limit the comment limit
     */
    public void setComment_limit(boolean comment_limit) {
        this.comment_limit = comment_limit;
    }


}
