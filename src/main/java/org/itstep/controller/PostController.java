package org.itstep.controller;

import org.itstep.domain.entity.Post;
import org.itstep.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String index() {
        return "post";
    }

    @PostMapping
    public String add(Post post) {
        postService.save(post);
        System.out.println("post = " + post);
        return "post";
    }

}
