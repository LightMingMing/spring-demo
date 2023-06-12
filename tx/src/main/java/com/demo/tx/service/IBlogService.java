package com.demo.tx.service;

import com.demo.entity.Blog;

public interface IBlogService {

    Blog select(int id);

    Blog selectByLocalCall(int id);

    void create(Blog blog);

}
