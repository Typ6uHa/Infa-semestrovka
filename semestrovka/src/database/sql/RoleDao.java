package database.sql;

import database.dao.AbstractDao;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Role> {

    public RoleDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM role";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO role (name) VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE role SET name = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM role WHERE id = ?";
    }

    @Override
    protected List<Role> parseResultSet(ResultSet rs) {
        List<Role> list = new ArrayList<>();
        try {
            while (rs.next()){
                Role role = new Role(rs.getInt("id") ,rs.getString("name"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) {
        try {
            statement.setString(1, object.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) {
        try {
            prepareStatementForInsert(statement, object);
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
