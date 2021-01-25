package org.itstep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
    @RequestMapping("/post")
    public String index() {
        return "post";
    }
}
