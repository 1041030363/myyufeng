CREATE SCHEMA 17play DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE 17play.user (
  
name varchar(45) NOT NULL,
  
password varchar(45) NOT NULL, 
PRIMARY KEY (name),
  
UNIQUE KEY uq_user_01 (name)
);

