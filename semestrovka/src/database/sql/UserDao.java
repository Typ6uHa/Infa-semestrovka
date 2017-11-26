package database.sql;

import database.dao.AbstractDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM \"user\"";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO \"user\"(login, password, nickname, name, " +
                "surname, city, photo_url, date_reg, role)" +
                "VALUES(?,?,?,?,?,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE \"user\" " +
                "SET login = ?, password = ?, nickname = ?," +
                "name = ?, surname = ?, city = ?, " +
                "photo_url = ?, date_reg = ?, role = ? " +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM \"user\" WHERE id = ?";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String nickname = rs.getString("nickname");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String city = rs.getString("city");
                String photoUrl = rs.getString("photo_url");
                Date date = rs.getDate("date_reg");
                int role = rs.getInt("role");
                User user = new User(id, login, password, nickname,
                        name, surname, city, photoUrl, date, role);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) {
        try {
            prepareStatementForInsert(statement, object);
            statement.setInt(10, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getNickname());
            statement.setString(4, object.getName());
            statement.setString(5, object.getSurname());
            statement.setString(6, object.getCity());
            statement.setString(7, object.getPhotoUrl());
            statement.setDate(8, (Date) object.getDateReg());
            statement.setInt(9, object.getRoleId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByLogin(String login){
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQuery()
                    + " WHERE login = ?");
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0){
            return null;
        }
        return list.iterator().next();
    }

    public User getUserByNickname(String nickname){
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQuery()
                    + " WHERE nickname = ?");
            statement.setString(1, nickname);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0){
            return null;
        }
        return list.iterator().next();
    }

    public List<User> getForSearch(String search){
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(getSelectQuery()
                    + " WHERE nickname ILIKE ? OR 'name' ILIKE ? " +
                    "OR surname ILIKE ? OR city ILIKE ?" );
            for (int i = 0; i < 4; i++) {
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
