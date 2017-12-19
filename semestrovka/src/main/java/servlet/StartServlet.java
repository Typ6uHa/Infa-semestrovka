package servlet;

import database.dao.DBConnection;
import database.sql.FavoriteDao;
import database.sql.TopicDao;
import helper.ServletHelper;
import model.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "StartServlet")
public class StartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
        List<Topic> topics = topicDao.getAll();

        ServletHelper.initCookie(request);
        ServletHelper.initAttributes(topics, request);

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }
}
