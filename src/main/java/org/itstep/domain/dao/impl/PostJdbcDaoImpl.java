package org.itstep.domain.dao.impl;

import org.itstep.domain.dao.PostDao;
import org.itstep.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class PostJdbcDaoImpl implements PostDao {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager txManager;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final CategoryJdbcDaoImpl categoryJdbcDao;

    @Autowired
    public PostJdbcDaoImpl(@Qualifier("dataSource") DataSource dataSource, PlatformTransactionManager txManager, CategoryJdbcDaoImpl categoryJdbcDao) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.categoryJdbcDao = categoryJdbcDao;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("posts")
                .usingColumns("title", "description", "category_id")
                .usingGeneratedKeyColumns("id");
        this.txManager = txManager;
    }


    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT id, title, description from posts",
                postRowMapper());
    }

    static RowMapper<Post> postRowMapper() {
        return (resultSet, i) -> Post.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .build();
    }

    public List<Post> findAllByCategoryId(Integer id) {
        return jdbcTemplate.query("SELECT id, title, description from posts where category_id=?",
                postRowMapper(), id);
    }

    @Override
    public Post findById(Integer integer) {
        return null;
    }

    @Transactional
    @Override
    public Integer save(Post data) {
        Integer id = null;
        Integer categoryId = null;

        categoryId = categoryJdbcDao.save(data.getCategory());

        if (data.getCategory() != null) {
            if (categoryJdbcDao.findByName(data.getCategory().getName()) == null) {
                categoryId = categoryJdbcDao.save(data.getCategory());
            } else {
                categoryId = categoryJdbcDao.findByName(data.getCategory().getName()).getId();
            }
        }

//        data.getCategory().setId(categoryId);

        id = simpleJdbcInsert
                .executeAndReturnKey(
                        new MapSqlParameterSource()
                        .addValue("title", data.getTitle())
                        .addValue("description", data.getDescription())
                        .addValue("category_id", data.getCategory().getId())
                )
                .intValue();
        return id;
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
