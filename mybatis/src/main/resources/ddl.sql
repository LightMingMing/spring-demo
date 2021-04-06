create table blog
(
    id         int primary key auto_increment,
    author     varchar(32),
    title      varchar(64),
    content    varchar(256),
    createTime datetime
);