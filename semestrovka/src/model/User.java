package model;

import database.dao.Identified;

import java.util.Date;

public class User implements Identified{
    private int id;
    private String login;
    private String password;
    private String nickname;
    private String name;
    private String surname;
    private String city;
    private String photoUrl;
    private Date dateReg;
    private int roleId;

    public User(String login, String password, String nickname, String name, String surname, String city) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public User(int id, String login, String password, String nickname, String name, String surname, String city, String photoUrl, Date dateReg, int roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.photoUrl = photoUrl;
        this.dateReg = dateReg;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
