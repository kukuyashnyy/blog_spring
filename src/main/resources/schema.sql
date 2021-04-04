create table if not exists categories
(
    id   int auto_increment
        primary key,
    name varchar(255) not null,
    constraint categories_name_uindex
        unique (name)
);

create table if not exists posts
(
    id          int auto_increment
        primary key,
    title       varchar(255) not null,
    description varchar(255) not null,
    category_id int          null,
    constraint posts_categories_id_fk
        foreign key (category_id) references categories (id)
);