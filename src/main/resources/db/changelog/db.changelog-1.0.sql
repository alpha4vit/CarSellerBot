-- liquibase formatted sql

--changeset roman_gurinovich:1
create table if not exists reviews
(
    id       bigserial primary key,
    value    varchar(255),
    video_id varchar(255)
);

--changeset roman_gurinovich:2
create table if not exists users
(
    chat_id   bigint      primary key ,
    name      varchar(50) not null,
    username  varchar(50),
    bot_state int         not null default 0,
    constraint unique_name_chat_id unique (chat_id, name)
);