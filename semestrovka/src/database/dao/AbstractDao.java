package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Identified> implements Dao<T> {

    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);

    @Override
    public void insert(T object) {
        try {
            PreparedStatement statement = connection.prepareStatement(getInsertQuery());
            prepareStatementForInsert(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById(int id) {
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQuery() + " WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) {
        try {
            PreparedStatement statement = connection.prepareStatement(getUpdateQuery());
            prepareStatementForUpdate(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T object) {
        try {
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());
            statement.setInt(1, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectQuery());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
