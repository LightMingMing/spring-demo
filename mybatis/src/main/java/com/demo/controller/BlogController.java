package com.demo.controller;

import com.demo.entity.Blog;
import com.demo.mapper.BlogMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器
 *
 * @author MingMing Zhao
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogMapper blogMapper;

    public BlogController(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @GetMapping
    public List<Blog> findAll() {
        return blogMapper.findAll();
    }

    @GetMapping("/{id}")
    public Blog get(@PathVariable int id) {
        return blogMapper.select(id);
    }

    @PostMapping
    public void create(@RequestBody Blog blog) {
        blogMapper.create(blog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        blogMapper.delete(id);
    }
}
