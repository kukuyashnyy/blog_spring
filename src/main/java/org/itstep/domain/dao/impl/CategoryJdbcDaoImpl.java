package org.itstep.domain.dao.impl;

import com.mysql.cj.protocol.Resultset;
import org.itstep.domain.dao.Dao;
import org.itstep.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CategoryJdbcDaoImpl implements Dao<Category, Integer> {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public CategoryJdbcDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert
                .withTableName("categories")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT id, name FROM categories",
                categoryRowMapper());
    }

    @Override
    public Category findById(Integer integer) {
        return null;
    }

    public Category findByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT id, name FROM categories where name=?",
                    categoryRowMapper(), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Integer save(Category data) {
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(data)).intValue();
    }

    @Override
    public Category update(Category data) {
        return null;
    }

    @Override
    public void delete(Integer integer) {
        jdbcTemplate.update("DELETE FROM categories WHERE id=?" , integer);
    }

    static RowMapper<Category> categoryRowMapper() {
        return (resultSet, i) -> Category.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
