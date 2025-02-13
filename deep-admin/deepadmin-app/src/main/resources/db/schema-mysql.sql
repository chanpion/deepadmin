CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `account`     varchar(64)  DEFAULT NULL COMMENT '登录账号',
    `username`    varchar(64)  DEFAULT NULL COMMENT '用户名',
    `password`    varchar(64)  DEFAULT NULL COMMENT '密码',
    `icon`        varchar(500) DEFAULT NULL COMMENT '头像',
    `email`       varchar(100) DEFAULT NULL COMMENT '邮箱',
    `nick_name`   varchar(200) DEFAULT NULL COMMENT '昵称',
    `note`        varchar(500) DEFAULT NULL COMMENT '备注信息',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `login_time`  datetime     DEFAULT NULL COMMENT '最后登录时间',
    `status`      int          DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用'
);


CREATE TABLE `sys_role`
(
    `id`          bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(100) DEFAULT NULL COMMENT '名称',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `status`      int          DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `sort`        int          DEFAULT '0'
);


CREATE TABLE `sys_permission`
(
    `id`          bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `pid`         bigint       DEFAULT NULL COMMENT '父级权限id',
    `name`        varchar(100) DEFAULT NULL COMMENT '名称',
    `value`       varchar(200) DEFAULT NULL COMMENT '权限值',
    `icon`        varchar(500) DEFAULT NULL COMMENT '图标',
    `type`        int          DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    `uri`         varchar(200) DEFAULT NULL COMMENT '前端资源路径',
    `status`      int          DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `sort`        int          DEFAULT NULL COMMENT '排序'
);


CREATE TABLE `sys_menu`
(
    `id`          bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `parent_id`   bigint       DEFAULT NULL COMMENT '父级ID',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `title`       varchar(100) DEFAULT NULL COMMENT '菜单名称',
    `level`       int          DEFAULT NULL COMMENT '菜单级数',
    `sort`        int          DEFAULT NULL COMMENT '菜单排序',
    `name`        varchar(100) DEFAULT NULL COMMENT '前端名称',
    `icon`        varchar(200) DEFAULT NULL COMMENT '前端图标',
    `hidden`      int          DEFAULT NULL COMMENT '前端隐藏'
);


CREATE TABLE `sys_resource`
(
    `id`          bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `name`        varchar(200) DEFAULT NULL COMMENT '资源名称',
    `url`         varchar(200) DEFAULT NULL COMMENT '资源URL',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `category_id` bigint       DEFAULT NULL COMMENT '资源分类ID'
);

-- 用户-角色关联表
CREATE TABLE `sys_user_role`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`)
);

-- 角色-权限关联表
CREATE TABLE `sys_role_permission`
(
    `role_id`       BIGINT NOT NULL,
    `permission_id` BIGINT NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`)
);