package database.sql;

import database.dao.AbstractDao;
import model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao extends AbstractDao<Comment> {

    public CommentDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM comment";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO comment(user_id, topic_id, comment_text) " +
                "VALUES(?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE comment SET user_id = ?, topic_id = ?, comment_text = ?, date_comment = ? " +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM comment WHERE id = ?";
    }

    @Override
    protected List<Comment> parseResultSet(ResultSet rs) {
        List<Comment> result = new ArrayList<>();
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int topicId = rs.getInt("topic_id");
                String text = rs.getString("comment_text");
                Date date = rs.getDate("date_comment");
                Comment comment = new Comment(userId, topicId, text, date);
                comment.setId(id);
                result.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Comment object) {
        try {
            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getTopicId());
            statement.setString(3, object.getComment());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Comment object) {
        prepareStatementForInsert(statement, object);
        try {
            statement.setInt(4, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentsByTopicId(int topic_id){
        List<Comment> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQuery()
                    + " WHERE topic_id = ?");
            statement.setInt(1, topic_id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0){
            return null;
        }
        return list;
    }

    public void deleteById(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
