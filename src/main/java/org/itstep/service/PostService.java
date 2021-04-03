package org.itstep.service;

import org.itstep.domain.dao.impl.PostJdbcDaoImpl;
import org.itstep.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

//    private final PostDaoImpl postDao;
//
//    @Autowired
//    public PostService(PostDaoImpl postDao) {
//        this.postDao = postDao;
//    }

    private final PostJdbcDaoImpl postDao;


    @Autowired
    public PostService(PostJdbcDaoImpl postDao) {
        this.postDao = postDao;
    }

    public void save(Post post) {
        postDao.save(post);
    }
}
