package servlet;

import database.dao.DBConnection;
import database.sql.FavoriteDao;
import database.sql.TopicDao;
import database.sql.UserDao;
import helper.ServletHelper;
import model.Topic;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {

    private final static String COOKIE_USER = "user_id";
    private final static String SESSION_USER = "current_user";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("q");

        initUsers(search, request);
        initTopics(search, request);

        request.setAttribute("current_url", request.getRequestURI());

        ServletHelper.initCookie(request);
        if (request.getSession().getAttribute(SESSION_USER) != null)
            request.setAttribute("login", false);
        else request.setAttribute("login", true);

        request.getRequestDispatcher("/search.ftl").forward(request, response);
    }

    private void initUsers(String search, HttpServletRequest request){
        UserDao userDao = new UserDao(DBConnection.getDBConnection());
        List<User> users = userDao.getForSearch(search);

        List<String> photoIds = new ArrayList<>();
        List<String> nickNames = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> surnames = new ArrayList<>();
        List<String> cities = new ArrayList<>();

        if (users != null){
            for (User user: users){
                if (user.getPhotoUrl() == null){
                    photoIds.add("../profile.png");
                }
                else photoIds.add(user.getPhotoUrl());
                nickNames.add(user.getNickname());
                names.add(user.getName());
                surnames.add(user.getSurname());
                cities.add(user.getCity());
            }
        }


        request.setAttribute("photos", photoIds);
        request.setAttribute("nicknames", nickNames);
        request.setAttribute("names", names);
        request.setAttribute("surnames", surnames);
        request.setAttribute("cities", cities);
    }

    private void initTopics(String search, HttpServletRequest request){
        TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
        List<Topic> topics = topicDao.getForSearch(search);

        List<String> themes = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<Integer> likes = new ArrayList<>();
        List<Integer> dislikes = new ArrayList<>();
        List<String> photoUrl = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if (topics !=null) {
            for (Topic topic: topics) {
                themes.add(topic.getTheme());
                description.add(topic.getDescription());
                likes.add(topic.getLike());
                dislikes.add(topic.getDislike());
                photoUrl.add(topic.getPhotoUrl());
                ids.add(topic.getId());
            }
        }


        request.setAttribute("themes", themes);
        request.setAttribute("description", description);
        request.setAttribute("likes", likes);
        request.setAttribute("dislikes", dislikes);
        request.setAttribute("photoUrl", photoUrl);
        request.setAttribute("id", ids);
    }
}
