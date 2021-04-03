package org.itstep.domain.dao.impl;

import org.itstep.domain.dao.PostDao;
import org.itstep.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostJdbcDaoImpl implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PostJdbcDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("posts")
                .usingColumns("title", "description");
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
        simpleJdbcInsert
                .execute(new BeanPropertySqlParameterSource(data));
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
        return null;
    }
}
