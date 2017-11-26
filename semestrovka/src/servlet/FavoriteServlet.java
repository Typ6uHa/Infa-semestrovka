package servlet;

import database.dao.DBConnection;
import database.sql.FavoriteDao;
import helper.ServletHelper;
import model.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FavoriteServlet")
public class FavoriteServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FavoriteDao favDao = new FavoriteDao(DBConnection.getDBConnection());
        List<Topic> topics = favDao.getTopicsByUserLogin(ServletHelper.getLogin(request));

        ServletHelper.initCookie(request);
        ServletHelper.initAttributes(topics, request);

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }


}
