package org.itstep.domain.dao.impl;

import lombok.val;
import org.itstep.domain.dao.PostDao;
import org.itstep.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostDaoImpl implements PostDao {

    private final DataSource dataSource;

    @Autowired
    public PostDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post findById(Integer integer) {
        return null;
    }

    @Override
    public void save(Post data) {
        try(Connection conn = getConnection()) {
            val stmt = conn.prepareStatement("INSERT into posts(title, description)" +
                    "values(?, ?)");
            stmt.setString(1, data.getTitle());
            stmt.setString(2, data.getDescription());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Post update(Post data) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
