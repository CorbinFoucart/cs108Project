-- Clears tables, creates a new database quiz social network inhabited by dogs.

USE c_cs108_cfoucart;

DROP TABLE IF EXISTS user_table, friends_table, message_table,
performance_table, quizzes_table, question_table, questions_table;
 -- remove tables if they already exist and start from scratch

CREATE TABLE user_table (
       username CHAR(64),
       password CHAR(64),
       privacy CHAR(64)
);

CREATE TABLE friends_table (
       friend_one CHAR(64),
       friend_two CHAR(64)
);

CREATE TABLE message_table (
       recipient CHAR(64),
       sender CHAR(64),
       message VARCHAR(1000),
       date_string CHAR(64),
       date_int BIGINT,
       was_read BOOLEAN,
       quiz_id CHAR(64)
);

CREATE TABLE performance_table (
       quiz_name CHAR(64),
       quiz_id CHAR(64),
       taken_by_user CHAR(64),
       score DECIMAL(5,4),
       date_string CHAR(64),
       date_long LONG
);

CREATE TABLE quizzes_table (
       quiz_name CHAR(64),
       quiz_id CHAR(64),
       date_string CHAR(64),
       date_int BIGINT,
       creator CHAR(64),
       quiz_bit_dump BLOB,
       category CHAR(64),
       tag_string VARCHAR(1000)
);

CREATE TABLE question_table (
       question_bit_dump BLOB,
       question_string VARCHAR(64),
       quiz_id CHAR(64)
);

-- Add dog users
INSERT INTO user_table VALUES
       ("bo", "bo", "private"),
       ("sarge", "sarge", "private"),
       ("scout", "scout", "private"),
       ("beethoven", "beethoven", "private"),
       ("air bud", "air bud", "private"),
       ("maggie", "maggie", "private"),
       ("oski", "oski", "private");

-- Add friend connections
INSERT INTO friends_table VALUES
       ("bo", "sarge"),
       ("sarge", "bo"),
       ("bo", "scout"),
       ("scout", "bo"),
       ("sarge", "scout"),
       ("scout", "sarge"),
       ("scout", "beethoven"),
       ("beethoven", "scout"),
       ("bo", "beethoven"),
       ("beethoven", "bo"),
       ("sarge", "beethoven"),
       ("beethoven", "sarge"),
       ("air bud", "beethoven"),
       ("beethoven", "air bud"),
       ("maggie", "oski"),
       ("oski", "maggie");
       

       
       
 
