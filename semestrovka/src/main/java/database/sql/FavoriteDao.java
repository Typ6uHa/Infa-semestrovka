package database.sql;

import database.dao.AbstractDao;
import database.dao.DBConnection;
import model.Favorite;
import model.Topic;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao extends AbstractDao<Favorite> {

    public FavoriteDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM favorites";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO favorites(user_id, topic_id) VALUES(?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE favorites SET user_id = ?, topic_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM favorites WHERE id = ?";
    }

    @Override
    protected List<Favorite> parseResultSet(ResultSet rs) {
        List<Favorite> result = new ArrayList<>();
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int topicId = rs.getInt("topic_id");
                Favorite favorite = new Favorite(userId, topicId);
                favorite.setId(id);
                result.add(favorite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Favorite object) {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getTopicId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Favorite object) {
        try {
            prepareStatementForInsert(statement, object);
            statement.setInt(3, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Favorite> getFavoritesByUserLogin(String login){
        UserDao userDao = new UserDao(DBConnection.getDBConnection());
        User user = userDao.getUserByLogin(login);
        List<Favorite> list = new ArrayList<>();
        if (user != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(getSelectQuery()
                        + " WHERE user_id = ?");
                statement.setInt(1, user.getId());
                ResultSet rs = statement.executeQuery();
                list = parseResultSet(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
        return null;
    }

    public List<Topic> getTopicsByUserLogin(String login){
        List<Favorite> favs = getFavoritesByUserLogin(login);
        List<Integer> topicIds = new ArrayList<>();
        if (favs != null) {
            for (Favorite fav : favs) {
                topicIds.add(fav.getTopicId());
            }
        }

        TopicDao topicDao = new TopicDao(DBConnection.getDBConnection());
        List<Topic> topics = new ArrayList<>();

        for (Integer topicId: topicIds){
            topics.add(topicDao.getById(topicId));
        }
        return topics;
    }

    public void deleteByUserAndTopicId(User user, int topicId){
        List<Favorite> favorites = getFavoritesByUserLogin(user.getLogin());
        boolean topic = false;
        Favorite favorite = null;
        for (int i = 0; i < favorites.size() && !topic; i++) {
            int id = favorites.get(i).getTopicId();
            if (id == topicId){
                topic = true;
                favorite = favorites.get(i);
            }
        }
        if (favorite != null){
            delete(favorite);
        }
    }
}
