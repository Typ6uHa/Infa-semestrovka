package ajax;

import database.dao.DBConnection;
import database.sql.FavoriteDao;
import helper.ServletHelper;
import model.Favorite;
import model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "AjaxFavoriteServlet")
public class AjaxFavoriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_value = request.getParameter("id");
        String action = request.getParameter("action");
        int topicId = Integer.parseInt(id_value);

        User user = ServletHelper.getUserFromDAO(request);

        FavoriteDao favoriteDao = new FavoriteDao(DBConnection.getDBConnection());
        Favorite favorite = new Favorite(user.getId(), topicId);

        if (action.equals("add")){
            favoriteDao.insert(favorite);
        }
        else {
            favoriteDao.deleteByUserAndTopicId(user, topicId);
        }
    }
}
