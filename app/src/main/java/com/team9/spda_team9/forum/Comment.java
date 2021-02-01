package com.team9.spda_team9.forum;

import java.time.LocalDateTime;

public class Comment {

    private int commentId = 1;
    private LocalDateTime commentOn;
    private int userId;
    private String body;

    public Comment(){}
    public Comment(int commentId, LocalDateTime commentOn){
        this.commentId = commentId;
        this.commentOn = commentOn;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getCommentOn() {
        return commentOn;
    }

    public void setCommentOn(LocalDateTime commentOn) {
        this.commentOn = commentOn;
    }
}
