package com.demo.tx.service;

import com.demo.entity.Blog;
import com.demo.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
public class BlogServiceCglib {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceCglib(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Transactional
    public Blog select(int id) {
        log.info("select(), transaction is active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        return blogMapper.select(id);
    }

    public Blog selectByLocalCall(int id) {
        log.info("selectByLocalCall(), transaction is active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        return select(id);
    }
}
