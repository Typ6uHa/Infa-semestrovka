package model;

import database.dao.Identified;

public class Favorite implements Identified{
    private int id;
    private int userId;
    private int topicId;

    public Favorite(int user_id, int topic_id) {
        this.userId = user_id;
        this.topicId = topic_id;
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
}
