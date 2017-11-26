package model;

import database.dao.Identified;

import java.util.Date;

public class Topic implements Identified{
    private int id;
    private int userId;
    private String theme;
    private String description;
    private String photoUrl;
    private int like;
    private int dislike;
    private Date date;

    public Topic(int id, int user_id, String theme, String description, String photoUrl, int like, int dislike, Date date) {
        this.id = id;
        this.userId = user_id;
        this.theme = theme;
        this.description = description;
        this.photoUrl = photoUrl;
        this.like = like;
        this.dislike = dislike;
        this.date = date;
    }

    public Topic(int userId, String theme, String description, String photoUrl) {
        this.userId = userId;
        this.theme = theme;
        this.description = description;
        this.photoUrl = photoUrl;
        like = 0;
        dislike = 0;

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Topic) obj).getId();
    }
}
