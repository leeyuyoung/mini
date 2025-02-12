-- CONNECTION: url=jdbc:oracle:thin:@//localhost:1521/XE




-- New script in localhost.
-- Connection Type: dev 
-- Url: jdbc:oracle:thin:@//localhost:1521/XE
-- workspace : C:\workspace\multi\03_DB
-- Date: 2024. 5. 12.
-- Time: 오후 6:22:03

CREATE USER MINI IDENTIFIED BY mini;
GRANT CONNECT, RESOURCE, DBA TO MINI;

-- TABLE 생성

CREATE TABLE MEMBERS(
    MEMBER_NUM VARCHAR2(100)  PRIMARY KEY,
    ID VARCHAR2(100) UNIQUE, -- NOT NULL-> UNIQUE 변경
    PW VARCHAR2(100) NOT NULL,
    NAME VARCHAR2(100),
    EMAIL_ID VARCHAR2(100),
    EMAIL_SITE VARCHAR2(100),   -- 추가!
    ADMIN NUMBER
    );
   
   

CREATE TABLE CARS(
	CAR_NUM VARCHAR2(100) PRIMARY KEY,
	CAR_NAME VARCHAR2(100) NOT NULL,
	CAR_CATEGORY VARCHAR2(100) NOT NULL,
	CAR_FEATURE VARCHAR2(100) NOT NULL,
	CAR_PREF_COMFORT NUMBER NOT NULL,
	CAR_PREF_WEIGHT NUMBER NOT NULL,
	CAR_PREF_PASSENGER NUMBER NOT NULL
--	(SELECT REVIEW_NUM FROM REVIEWS WHERE  )AS 별점 총합/리뷰개수 추가하기!
	);

CREATE TABLE PRODUCTS(
	PRODUCT_NUM VARCHAR2(100) PRIMARY KEY,
	CAR_NUM VARCHAR2(100) REFERENCES CARS(CAR_NUM),
	PRODUCT_STATUS VARCHAR2(100),
	PRODUCT_PRICE NUMBER NOT NULL,
	PRODUCT_AVAILABLE NUMBER NOT NULL
	);

CREATE TABLE ORDERS(
	ORDER_NUM VARCHAR2(100) PRIMARY KEY,
	MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
	--CAR_NUM VARCHAR2(100) REFERENCES CARS(CAR_NUM), // 차량번호(X) 차량고유번호PRODUCT_NUM 요게 맞습니다! 아랫줄은 수정내
	PRODUCT_NUM VARCHAR2(100) REFERENCES PRODUCTS(PRODUCT_NUM),
	ORDER_STATUS NUMBER NOT NULL,
	ORDER_REFUND_REQUEST NUMBER NOT NULL,
	ORDER_REFUND_COMPLETE NUMBER NOT NULL
	);

CREATE TABLE REVIEWS(
    REVIEW_NUM VARCHAR2(100) PRIMARY KEY,
    ORDER_NUM VARCHAR2(100) REFERENCES ORDERS(ORDER_NUM),
    RATING NUMBER NOT NULL,
    TITLE VARCHAR2(100) NOT NULL,
    CONTENTS VARCHAR2(1000) NOT NULL
    );
   

CREATE TABLE PRESETS(
	PRESET_NUM VARCHAR2(100) PRIMARY KEY,
	MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
	PRESET_COMFORT NUMBER NOT NULL,
	PRESET_WEIGHT NUMBER NOT NULL,
	PRESET_PASSENGER NUMBER NOT NULL
	);


--CREATE TABLE PAYMENTS (
--      PAYMENT_NUM VARCHAR2(100) PRIMARY KEY,
--      ORDER_NUM VARCHAR2(100) NOT NULL,
--      PAYMENT_AMOUNT NUMBER NOT NULL,
--      PAYMENT_METHOD VARCHAR2(50) NOT NULL,
--      CONSTRAINT FK_PAYMENT_ORDER
--          FOREIGN KEY (ORDER_NUM)
--              REFERENCES ORDERS(ORDER_NUM)
--              ON DELETE CASCADE
--);


CREATE TABLE payments (
      ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
      PAYMENT_NUM VARCHAR2(50) NOT NULL,
      ORDER_NUM VARCHAR2(50) NOT NULL,
      PAYMENT_AMOUNT NUMBER(10, 2) NOT NULL,
      PAYMENT_METHOD VARCHAR2(50) NOT NULL,
      PRIMARY KEY (ID),
      CONSTRAINT FK_ORDER FOREIGN KEY (ORDER_NUM) REFERENCES orders(ORDER_NUM)
);




-- SEQUENCE 생성

CREATE SEQUENCE MEMBER_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE REVIEW_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE CAR_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE PRODUCT_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE PRESET_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE ORDER_NUM_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;


CREATE SEQUENCE PAYMENT_NUM_SEQ START WITH 1 INCREMENT BY 1;



SELECT * FROM PRODUCTS;
SELECT * FROM ORDERS;
SELECT * FROM CARS;
SELECT * FROM MEMBERS;

INSERT INTO MEMBERS values ('M'||MEMBER_NUM_SEQ.NEXTVAL,'admin','admin','관리자','admin','@gmail.com',1);
INSERT INTO MEMBERS values ('M'||MEMBER_NUM_SEQ.NEXTVAL,'1','1','1','1','@1',0);


INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'Genesis GV80', '준중형차','편안', 1,1,1);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'KIA K7', '준중형차','편안', 1,1,0);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'Genesis G80', '준중형차','편안', 1,0,1);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'Genesis G70', '준중형차','편안', 1,0,0);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'Hyundai 투싼', '준중형차','편안', 0,1,1);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'KIA 레이', '준중형차','편안', 0,1,0);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'KIA 셀토스', '준중형차','편안', 0,0,1);
INSERT INTO CARS VALUES('C'||CAR_NUM_SEQ.NEXTVAL, 'Hyundai 아반떼', '준중형차','편안', 0,0,0);

INSERT INTO PRODUCTS VALUES ('P'||PRODUCT_NUM_SEQ.NEXTVAL, 'C1', 1, 5000, 4);
INSERT INTO ORDERS VALUES ('O'||ORDER_NUM_SEQ.NEXTVAL, 'M2', 'P1',1,1,1);
--INSERT INTO REVIEWS values (REVIEW_NUM_SEQ.NEXTVAL,'O2',3 ,'A','ABC');


COMMIT;



CREATE OR REPLACE VIEW V_REVIEW_TABLE
( REVIEW_NUM , TITLE, CAR_NUM, WRITER, ORDER_NUM, RATING, CONTENTS)
AS
SELECT 
    B.REVIEW_NUM,
    B.TITLE,
    C.CAR_NUM,
    D.ID,
    A.ORDER_NUM,
    B.RATING,
    B.CONTENTS
FROM ORDERS A    
LEFT JOIN REVIEWS B ON A.ORDER_NUM = B.ORDER_NUM 
LEFT JOIN PRODUCTS C ON A.PRODUCT_NUM = C.PRODUCT_NUM
LEFT JOIN MEMBERS D ON A.MEMBER_NUM = D.MEMBER_NUM;
--

INSERT INTO REVIEWS VALUES (''||REVIEW_NUM_SEQ.NEXTVAL, 'O2', 3, '후기','내용');
INSERT INTO PRODUCTS VALUES ('P'||PRODUCT_NUM_SEQ.NEXTVAL, 'C2', 1, 5000, 4);
INSERT INTO ORDERS VALUES ('O'||ORDER_NUM_SEQ.NEXTVAL, 'M2', 'P2',1,1,1);
INSERT INTO REVIEWS VALUES (''||REVIEW_NUM_SEQ.NEXTVAL, 'O4', 2, '후기1','내용1');



DROP TABLE PRESETS;
DROP TABLE REVIEWS;
DROP TABLE ORDERS;
DROP TABLE PRODUCTS;
DROP TABLE CARS;
DROP TABLE MEMBERS;


DROP SEQUENCE MEMBER_NUM_SEQ;
DROP SEQUENCE CAR_NUM_SEQ;
DROP SEQUENCE PRODUCT_NUM_SEQ;
DROP SEQUENCE ORDER_NUM_SEQ;
DROP SEQUENCE REVIEW_NUM_SEQ;
DROP SEQUENCE PRESET_NUM_SEQ;


SELECT * FROM  V_REVIEW_TABLE WHERE REVIEW_NUM IS NOT NULL AND WRITER = '1';


INSERT INTO ORDERS VALUES ('O'||ORDER_NUM_SEQ.NEXTVAL, 'M2', 'P2',1,1,1);
INSERT INTO ORDERS VALUES ('O'||ORDER_NUM_SEQ.NEXTVAL, 'M2', 'P1',1,1,1);







