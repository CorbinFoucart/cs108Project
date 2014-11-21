-- Clears tables, creates a new database quiz social network inhabited by dogs.

USE c_cs108_cfoucart;

DROP TABLE IF EXISTS user_table, friends_table, message_table, categories_table,
performance_table, quizzes_table, question_table, achievement_table, questions_table;
 -- remove tables if they already exist and start from scratch

CREATE TABLE user_table (
       username CHAR(64),
       password CHAR(64),
       privacy INT, 
       admin_status BOOLEAN
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
       date_long BIGINT,
       was_read BOOLEAN,
       quiz_id CHAR(64),
       message_type CHAR(64)
);

CREATE TABLE performance_table (
       quiz_name CHAR(64),
       quiz_id CHAR(64),
       taken_by_user CHAR(64),
       score DECIMAL(5,4),
       date_string CHAR(64),
       date_long BIGINT
);

CREATE TABLE quizzes_table (
       quiz_name CHAR(64),
       quiz_id CHAR(64),
       date_string CHAR(64),
       date_long BIGINT,
       creator CHAR(64),
       quiz_bit_dump BLOB,
       category CHAR(64),
       tag_string VARCHAR(1000)
);

CREATE TABLE achievement_table (
       username CHAR(64),
       amateur_author BOOLEAN,
       prolific_author BOOLEAN,
       prodigious_author BOOLEAN,
       quiz_machine BOOLEAN,
       i_am_the_greatest BOOLEAN,
       practice_makes_perfect BOOLEAN      
);

CREATE TABLE question_table (
       question_bit_dump BLOB,
       question_string VARCHAR(64),
       quiz_id CHAR(64)
);

-- Add dog users
INSERT INTO user_table VALUES
       ("bo", "bobo", 0, false),
       ("sarge", "sargesarge", 0, true),
       ("scout", "scoutscout", 1, false),
       ("beethoven", "beethovenbeethoven", 0, false),
       ("air bud", "air budair bud", 0, false ),
       ("maggie", "maggiemaggie", 0, false),
       ("oski", "oskioski", 2, false);

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

INSERT INTO achievement_table VALUES
       ("bo", true, false, false, false, false,true),
       ("sarge", true, true, true, true, true, true);

-- Add some sample messages for the dogs
-- As of now, contain no messages where quiz object is not null
INSERT INTO message_table VALUES
       -- note messages
       ("bo", "sarge", "I was just diagnosed with irritable bowel syndrome.", "201402140643", 201402140643, true, null, "note"),
       ("sarge", "bo", "I am sorry to hear that, bo.", "201402140743", 201402140743, true, null, "note"),
       ("air bud", "beethoven", "My 90s movies were much better than yours.", "201403142359", 201403142359, true, null, "note"),
       ("beethoven", "air bud", "factual. want to grab slushies later?", "201403150000", 201403150000, false, null, "note"),

        -- admin notification messages 
       ("bo", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, false, null, "announcement"),
       ("scout", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, true, null, "announcement"),
       ("beethoven", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, false, null, "announcement"),
       ("air bud", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, true, null, "announcement"),
       ("maggie", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, false, null, "announcement"),
       ("oski", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, true, null, "announcement"),
       ("sarge", "sarge", "Reminder: all quizzes with cat subject matter will be flagged and removed!", "201312230123", 201312230123, false, null,  "announcement"),

       -- challenge messages
       ("sarge", "bo", "Dog Test 1", "201312230123", 201312230123, false, null,  "challenge"),
       ("air bud", "beethoven", "Dog Test 2", "201312230123", 201312230123, false, null,  "challenge"),
       ("sarge", "bo", "Dog Test 1", "201312230123", 201312230123, true,null,  "challenge"),
       ("oski", "maggie", "Dog Test 2", "201312230123", 201312230123, true, null, "challenge"),

       -- friend request messages
       ("bo", "maggie", "Add me as a friend!", "201312230123", 201312230123, false, null, "friend_request"),
       ("beethoven", "sarge", "Add me as a friend!", "201312230123", 201312230123, true, null, "friend_request"),
       ("scout", "maggie", "Add me as a friend!", "201312230123", 201312230123, false, null, "friend_request"),
       ("beethoven", "bo", "Add me as a friend!", "201312230123", 201312230123, true, null, "friend_request");
   
       

       

       
       
 
