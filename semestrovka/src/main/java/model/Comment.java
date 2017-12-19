package model;

import database.dao.Identified;

import java.util.Date;

public class Comment implements Identified{
    private int id;
    private int userId;
    private int topicId;
    private String comment;
    private Date date;

    public Comment(int user_id, int topic_id, String comment, Date date) {
        this.userId = user_id;
        this.topicId = topic_id;
        this.comment = comment;
        this.date = date;
    }

    public Comment(int user_id, int topic_id, String comment) {
        this.userId = user_id;
        this.topicId = topic_id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
