DROP TABLE IF EXISTS unused_key;
create table unused_key (
     id bigint AUTO_INCREMENT,
     key varchar(8)
);


DROP TABLE IF EXISTS used_key;
create table used_key (
     id bigint AUTO_INCREMENT,
     key varchar(8)
);
