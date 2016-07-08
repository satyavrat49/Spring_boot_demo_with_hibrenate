DROP DATABASE IF EXISTS testdatabase;

CREATE DATABASE testdatabase DEFAULT CHARACTER SET utf8 ;

USE  testdatabase;

DROP TABLE IF EXISTS  USER_MST;
DROP TABLE IF EXISTS  ROLE_MST;

CREATE TABLE ROLE_MST (
 ROLE_ID VARCHAR(40) NOT NULL,
 ROLE VARCHAR(40) NOT NULL,
 CREATED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 UPDATED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (ROLE_ID)
);

CREATE TABLE USER_MST (
 USER_ID VARCHAR(40) NOT NULL,
 
 USERNAME VARCHAR(40) NULL,
 EMAIL VARCHAR(80)  NULL,
 FIRST_NAME VARCHAR(80) NOT NULL,
 LAST_NAME VARCHAR(80) ,
 PASSWORD VARCHAR(80) NOT NULL,
 EMAIL_VERIFICATION_TOKEN VARCHAR(80) NULL,
 TOKEN VARCHAR(200) NULL,
 ROLE_ID VARCHAR(40) NOT NULL,
 USER_STATE SET('ACTIVE','INACTIVE') NOT NULL, 
 CREATED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 UPDATED_DATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (USER_ID),
 UNIQUE(USERNAME),
 FOREIGN KEY (ROLE_ID) REFERENCES ROLE_MST(ROLE_ID)
);
INSERT INTO ROLE_MST VALUES('1','ADMIN',NULL,NULL),('2','DEVELOPER',NULL,NULL),('3','USER',NULL,NULL);
INSERT INTO USER_MST VALUES('1','raunak','raunak.nayak@gmail.com','raunak','nayak','Login1-2',NULL,NULL,'1','ACTIVE',NULL,NULL),('2','Ashish','ashish.jain@gmail.com','Ashish','Jain','Login1-2',NULL,NULL,'2','ACTIVE',NULL,NULL),('3','Ram','ram.sharma020@gmail.com','Ram','Mehar','Login1-2',NULL,NULL,'3','ACTIVE',NULL,NULL);