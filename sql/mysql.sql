create database  yupi;
create table yupi.user
(
    username     varchar(256)                       null comment '用户昵称',
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号'
)
    comment '用户';

INSERT INTO yupi.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('鱼皮', 1, 'yupi', 'https://himg.bdimg.com/sys/portraitn/item/public.1.e137c1ac.yS1WqOXfSWEasOYJ2-0pvQ', null, 'b0dd3697a192885d7c055db46155b26a', null, null, 0, '2023-08-06 14:14:22', '2024-03-30 15:56:36', 0, 1, '12345');
INSERT INTO yupi.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('douyu', 1770451462350995457, '123', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.huitu.com%2Fdesign%2Fshow%2F20220805%2F104321059070.html&psig=AOvVaw1GXU8568LNFgdFBaEKjkrB&ust=1711029436069000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOi9vMj_goUDFQAAAAAdAAAAABAE', 0, '123', '123', '456', 0, '2024-03-20 22:04:58', '2024-03-20 22:04:58', 0, 0, null);
INSERT INTO yupi.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('douyu', 1770451663929315329, '123', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.huitu.com%2Fdesign%2Fshow%2F20220805%2F104321059070.html&psig=AOvVaw1GXU8568LNFgdFBaEKjkrB&ust=1711029436069000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOi9vMj_goUDFQAAAAAdAAAAABAE', 0, '123', '123', '456', 0, '2024-03-20 22:05:46', '2024-03-20 22:05:46', 0, 0, null);
INSERT INTO yupi.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('douyu', 1770451809719152642, '123', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.huitu.com%2Fdesign%2Fshow%2F20220805%2F104321059070.html&psig=AOvVaw1GXU8568LNFgdFBaEKjkrB&ust=1711029436069000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOi9vMj_goUDFQAAAAAdAAAAABAE', 0, '123', '123', '456', 0, '2024-03-20 22:06:20', '2024-03-20 22:06:20', 0, 0, null);
INSERT INTO yupi.user (username, id, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES (null, 1771861093401022466, 'abc a', null, null, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, 0, '2024-03-24 19:26:18', '2024-03-24 19:26:18', 0, 0, null);
