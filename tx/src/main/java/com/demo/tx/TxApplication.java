package com.demo.tx;

import com.demo.entity.Blog;
import com.demo.tx.service.BlogServiceCglib;
import com.demo.tx.service.IBlogService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.demo.mapper", "com.demo.tx"})
@EnableTransactionManagement
@MapperScan("com.demo.mapper")
@Slf4j
public class TxApplication implements CommandLineRunner {

    private final IBlogService blogService;

    private final BlogServiceCglib blogServiceCglib;

    private final DataSourceTransactionManager dataSourceTransactionManager;

    public TxApplication(IBlogService blogService, BlogServiceCglib blogServiceCglib, DataSourceTransactionManager dataSourceTransactionManager) {
        this.blogService = blogService;
        this.blogServiceCglib = blogServiceCglib;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        insertOneBlog();
        log.info("====================================================");
        log.info("DataSourceTransactionManager: {}", dataSourceTransactionManager.getClass());
        log.info("BlogServiceJdkDynamic:        {}", blogService.getClass());
        log.info("BlogServiceCglib:             {}", blogServiceCglib.getClass());

        log.info("{}", blogServiceCglib.select(1));
        log.info("{}", blogServiceCglib.selectByLocalCall(1));
        log.info("{}", blogService.select(1));
        log.info("{}", blogService.selectByLocalCall(1));
        log.info("====================================================");
    }

    private void insertOneBlog() {
        Blog blog = new Blog();
        blog.setAuthor("MingMing");
        blog.setTitle("Spring");
        blog.setContent("Spring");
        blogService.create(blog);
    }
}