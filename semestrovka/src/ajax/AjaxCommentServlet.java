package ajax;

import database.dao.DBConnection;
import database.sql.CommentDao;
import database.sql.FavoriteDao;
import helper.ServletHelper;
import model.Favorite;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxCommentServlet")
public class AjaxCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_value = request.getParameter("id");;
        int commentId = Integer.parseInt(id_value);

        CommentDao commentDao = new CommentDao(DBConnection.getDBConnection());

        commentDao.deleteById(commentId);

    }
}
