DROP TABLE IF EXISTS user;
CREATE TABLE user (
user_name varchar(100) NOT NULL,
password varchar(50) NOT NULL,
create_user varchar(100) NOT NULL,
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_user varchar(100) NULL, 
update_time timestamp NULL,
PRIMARY KEY (user_name)
);

DROP TABLE IF EXISTS log;
CREATE TABLE log (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_name varchar(100) NOT NULL,
do_action varchar(20) NOT NULL,
error_message varchar(255) NULL,
is_success boolean NOT NULL DEFAULT FALSE,
log_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 
log_date int(4) NOT NULL,
PRIMARY KEY (id, log_date),
KEY `k_username_doaction` (user_name, do_action),
KEY `k_action` (do_action)
)
PARTITION BY RANGE (log_date) (
  PARTITION p0 VALUES LESS THAN (2019),
  PARTITION p1 VALUES LESS THAN (2020),
  PARTITION p2 VALUES LESS THAN (2021),
  PARTITION p3 VALUES LESS THAN (2022),
  PARTITION p4 VALUES LESS THAN (2023),
  PARTITION p5 VALUES LESS THAN MAXVALUE
)
;