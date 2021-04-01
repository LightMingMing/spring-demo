package com.demo.mapper;

import com.demo.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射
 *
 * @author MingMing Zhao
 */
@Repository
public interface BlogMapper {

    Blog select(int id);

    List<Blog> findAll();

    void create(Blog blog);

    void delete(int id);
}
