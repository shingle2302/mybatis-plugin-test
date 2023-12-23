insert into t_user (username, password, email, created_at, updated_at)
values ('admin', 'admin', 'admin@admin', now(), now()),
       ('user1', 'user1', 'user@user', now(), now()),
       ('user2', 'user2', 'user@user', now(), now()),
       ('user3', 'user3', 'user@user', now(), now()),
       ('user4', 'user4', 'user@user', now(), now()),
       ('user5', 'user5', 'user@user', now(), now()),
       ('user6', 'user6', 'user@user', now(), now()),
       ('user7', 'user7', 'user@user', now(), now()),
       ('user8', 'user8', 'user@user', now(), now()),
       ('user9', 'user9', 'user@user', now(), now()),
       ('user10', 'user10', 'user@user', now(), now()),
       ('user11', 'user11', 'user@user', now(), now()),
       ('user12', 'user12', 'user@user', now(), now()),
       ('user13', 'user13', 'user@user', now(), now()),
       ('user14', 'user14', 'user@user', now(), now());


insert into t_permission_object (permission_code, permission_name, created_at, updated_at)
values ('user:add', '新增用户', now(), now()),
       ('user:update', '修改用户', now(), now()),
       ('user:delete', '删除用户', now(), now()),
       ('user:query', '查询用户', now(), now()),
       ('user:query:all', '查询所有用户', now(), now());


insert into t_permission_rule (permission_object_id, permission_dimension, permission_condition, permission_logic,
                               group_no, created_at, updated_at)
values (5, 'username', '=', 'and', 1, now(), now()),
       (5, 'password', '=', 'and', 1, now(), now()),
       (5, 'email', 'in', 'or', 2, now(), now());
