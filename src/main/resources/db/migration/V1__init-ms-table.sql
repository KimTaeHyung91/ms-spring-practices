create table if not exists ms.student
(
    id                varchar(255)                        not null
        primary key,
    age               bigint                              not null,
    city              varchar(255)                        not null,
    numbering_address varchar(255)                        null,
    road_address      varchar(255)                        null,
    student_name      varchar(255)                        not null,
    created_at        timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    updated_at        timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    deleted_at        timestamp                           null

) default charset = utf8mb4
  engine = InnoDB;

create table if not exists ms.school_page
(
    id                varchar(255)                        not null
        primary key,
    school_name       varchar(255)                        not null,
    city              varchar(255)                        not null,
    numbering_address varchar(255)                        null,
    road_address      varchar(255)                        null,
    created_at        timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    updated_at        timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    deleted_at        timestamp                           null
) default charset = utf8mb4
  engine = InnoDB;

create table if not exists student_school_page
(
    id                  varchar(255)                        not null
        primary key,
    subscribe_at        datetime(6)                         not null,
    cancel_subscribe_at datetime(6)                         null,
    subscribe_status    enum ('ACTIVE', 'IN_ACTIVE')        null,
    school_page_id      varchar(255)                        null,
    student_id          varchar(255)                        null,
    created_at          timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    updated_at          timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    deleted_at          timestamp                           null
) default charset = utf8mb4
  engine = InnoDB;

