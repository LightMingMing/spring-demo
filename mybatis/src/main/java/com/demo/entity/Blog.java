package com.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * Blog 实体
 *
 * @author MingMing Zhao
 */
@Data
public class Blog {

    private Integer id;

    private String author;

    private String title;

    private String content;

    private Date createTime;
}
