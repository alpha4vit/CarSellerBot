-- liquibase formatted sql

--changeset roman_gurinovich:1
create table if not exists reviews(
    id bigserial primary key,
    value varchar(255),
    video_id varchar(255)
);