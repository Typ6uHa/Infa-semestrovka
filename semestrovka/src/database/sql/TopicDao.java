package database.sql;

import database.dao.AbstractDao;
import model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicDao extends AbstractDao<Topic> {


    public TopicDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM topic";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO topic (user_id, theme, description, " +
                "photo_url, \"like\", dislike) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE topic SET user_id = ?, theme = ?, description = ?, " +
                "photo_url = ?, \"like\" = ?, dislike = ? WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM topic WHERE id = ?;";
    }

    @Override
    protected List<Topic> parseResultSet(ResultSet rs) {
        List<Topic> result = new ArrayList<>();
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String theme = rs.getString("theme");
                String description = rs.getString("description");
                String photoUrl = rs.getString("photo_url");
                int like = rs.getInt("like");
                int dislike = rs.getInt("dislike");
                Date date = rs.getDate("date_post");
                Topic topic = new Topic(id ,userId, theme, description,
                        photoUrl, like, dislike, date);
                result.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Topic object) {
        try {
            statement.setInt(1, object.getUserId());
            statement.setString(2, object.getTheme());
            statement.setString(3, object.getDescription());
            statement.setString(4, object.getPhotoUrl());
            statement.setInt(5, object.getLike());
            statement.setInt(6, object.getDislike());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Topic object) {
        try {
            prepareStatementForInsert(statement, object);
            statement.setInt(7, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Topic> getForSearch(String search){
        List<Topic> result = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(getSelectQuery()
                    + " WHERE theme ILIKE ? OR description ILIKE ?");
            for (int i = 0; i < 2; i++) {
                st.setString(i + 1, "%" + search + "%");
            }

            ResultSet rs = st.executeQuery();
            result = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.size() == 0){
            return null;
        }
        return result;
    }
}
