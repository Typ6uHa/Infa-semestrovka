package servlet;

import database.dao.DBConnection;
import database.sql.CommentDao;
import database.sql.FavoriteDao;
import database.sql.TopicDao;
import database.sql.UserDao;
import helper.ServletHelper;
import model.Comment;
import model.Topic;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "TopicServlet")
public class TopicServlet extends HttpServlet {

    private final static String COOKIE_USER = "user_id";
    private final static String SESSION_USER = "current_user";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initAll(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initAll(request, response);
    }

    private void initAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic_id = request.getParameter("id");
        int id = Integer.parseInt(topic_id);

        ServletHelper.initCookie(request);

        FavoriteDao favDao = new FavoriteDao(DBConnection.getDBConnection());
        List<Topic> favTopics = favDao.getTopicsByUserLogin(ServletHelper.getLogin(request));

        TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
        Topic topic = topicDao.getById(id);

        User current_user = ServletHelper.getUserFromDAO(request);

        if (request.getSession().getAttribute(SESSION_USER) != null){
            request.setAttribute("login", false);
            addCommentary(request, id, current_user);
        }
        else request.setAttribute("login", true);

        String theme = topic.getTheme();
        String description = topic.getDescription();
        int likes = topic.getLike();
        int dislikes = topic.getDislike();
        String photoUrl = topic.getPhotoUrl();
        boolean favorite = favTopics.contains(topic);
        Date date = topic.getDate();

        CommentDao commentDao = new CommentDao(DBConnection.getDBConnection());
        List<Comment> comments = commentDao.getCommentsByTopicId(id);

        UserDao userDao = new UserDao(DBConnection.getDBConnection());

        List<String> userPhotos = new ArrayList<>();
        List<String> userNames = new ArrayList<>();
        List<String> texts = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<Boolean> deletable = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if (comments != null){
            for (Comment comment: comments){
                User user = userDao.getById(comment.getUserId());
                if (user.getPhotoUrl() == null){
                    userPhotos.add("../profile.png");
                }
                else userPhotos.add(user.getPhotoUrl());
                userNames.add(user.getNickname());
                texts.add(comment.getComment());
                dates.add(comment.getDate());
                deletable.add(current_user.getId() == user.getId());
                ids.add(comment.getId());
            }
        }

        request.setAttribute("user_photos", userPhotos);
        request.setAttribute("user_names", userNames);
        request.setAttribute("comments", texts);
        request.setAttribute("comment_date", dates);
        request.setAttribute("deletable", deletable);
        request.setAttribute("ids", ids);


        request.setAttribute("date", date);
        request.setAttribute("favorite", favorite);
        request.setAttribute("theme", theme);
        request.setAttribute("description", description);
        request.setAttribute("likes", likes);
        request.setAttribute("dislikes", dislikes);
        request.setAttribute("photoUrl", photoUrl);
        request.setAttribute("id", id);
        request.setAttribute("current_url", request.getRequestURI());


        request.getRequestDispatcher("topic.ftl").forward(request, response);
    }

    private void addCommentary(HttpServletRequest request, int topic_id, User user){
        CommentDao commentDao = new CommentDao(DBConnection.getDBConnection());
        String commentary = request.getParameter("commentary");


        if (commentary != null && !commentary.equals("")){
            Comment comment = new Comment(user.getId(), topic_id, commentary);

            commentDao.insert(comment);
        }
    }
}
