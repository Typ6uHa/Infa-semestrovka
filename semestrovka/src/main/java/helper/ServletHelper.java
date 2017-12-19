package helper;

import database.dao.DBConnection;
import database.sql.FavoriteDao;
import database.sql.UserDao;
import model.Topic;
import model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ServletHelper {

    private final static String COOKIE_USER = "user_id";
    private final static String SESSION_USER = "current_user";

    public static String getLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String login = "";
        if (cookies != null){
            for (Cookie cookie : cookies){
                switch (cookie.getName()){
                    case COOKIE_USER:
                        login = cookie.getValue();
                        break;
                }
            }
        }
        return login;
    }

    public static void initCookie(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                switch (cookie.getName()){
                    case COOKIE_USER:
                        request.getSession().setAttribute(SESSION_USER, cookie.getValue());
                        break;
                }
            }
        }
    }

    public static void initAttributes(List<Topic> topics, HttpServletRequest request){

        FavoriteDao favDao = new FavoriteDao(DBConnection.getDBConnection());
        List<Topic> favTopics = getLogin(request).isEmpty() ? new ArrayList<>() : favDao.getTopicsByUserLogin(getLogin(request));

        String sort = request.getParameter("sort");
        String pagePar = request.getParameter("page");

        int page = pagePar == null ? 1 : Integer.parseInt(pagePar);

        if (sort == null || "new".equals(sort)){
            topics.sort((topic1, topic2) -> {
                if (topic2.getDate().before(topic1.getDate())) return -1;
                else
                if (topic1.getDate().before(topic2.getDate())) return 1;
                return 0;
            });
            request.setAttribute("sort", "new");
        }
        else {
            topics.sort((o1, o2) -> {
                if (o1.getLike() - o1.getDislike() < o2.getLike() - o2.getDislike()) return 1;
                else
                if (o1.getLike() - o1.getDislike() > o2.getLike() - o2.getDislike()) return -1;
                return 0;
            });
            request.setAttribute("sort", "popular");
        }
        int user_role;
        List<String> themes = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<Integer> likes = new ArrayList<>();
        List<Integer> dislikes = new ArrayList<>();
        List<String> photoUrl = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<Topic> pageTopic = new ArrayList<>();
        List<Boolean> favorites = new ArrayList<>();
        for (int i = (page - 1) * 3; i < page * 3 && i < topics.size(); i++) {
            Topic topic = topics.get(i);
            pageTopic.add(topic);
            favorites.add(favTopics.contains(topic));
            themes.add(topic.getTheme());
            description.add(topic.getDescription());
            likes.add(topic.getLike());
            dislikes.add(topic.getDislike());
            photoUrl.add(topic.getPhotoUrl());
            ids.add(topic.getId());
        }

        int maxPages = topics.size() % 3 > 0 ? topics.size() / 3 + 1 : (topics.size() / 3);

        request.setAttribute("favorite", favorites);
        request.setAttribute("page", page);
        request.setAttribute("max_pages", maxPages);
        request.setAttribute("topics", pageTopic);
        request.setAttribute("themes", themes);
        request.setAttribute("description", description);
        request.setAttribute("likes", likes);
        request.setAttribute("dislikes", dislikes);
        request.setAttribute("photoUrl", photoUrl);
        request.setAttribute("id", ids);
        request.setAttribute("current_url", request.getRequestURI());

        if (request.getSession().getAttribute(SESSION_USER) != null) {
            request.setAttribute("login", false);
            request.setAttribute("user_role", getUserFromDAO(request).getRoleId());
        }
        else request.setAttribute("login", true);
    }

    public static User getUserFromDAO(HttpServletRequest request) {
        UserDao userDao = new UserDao(DBConnection.getDBConnection());
        return userDao.getUserByLogin(getLogin(request));
    }
}
