create table IF NOT EXISTS blog
(
    id         int primary key auto_increment,
    author     varchar(32)  NOT NULL,
    title      varchar(64)  NOT NULL,
    content    varchar(256) NOT NULL,
    createTime datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP
);