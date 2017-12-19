package ajax;

import database.dao.DBConnection;
import database.sql.CommentDao;
import database.sql.TopicDao;
import model.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxDeletePost")
public class AjaxDeletePost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_value = request.getParameter("id");;
        int topicId = Integer.parseInt(id_value);

        TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
        topicDao.deleteTopicById(topicId);
    }
}
