package org.itstep.domain.dao;

import org.itstep.domain.entity.Post;

import java.sql.Connection;
import java.sql.SQLException;

public interface PostDao extends Dao<Post, Integer>{
    Connection getConnection() throws SQLException;
}
