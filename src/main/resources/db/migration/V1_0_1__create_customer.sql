CREATE TABLE "order"."customer"
(
    "id" INTEGER NOT NULL,
    "created_at" DATE NOT NULL,
    "updated_at" DATE,
    "city" VARCHAR(50),
    "email" VARCHAR(50),
    "name" VARCHAR(50),
    "phone" VARCHAR(20),
    "profile_pic" VARCHAR(255),
    "state" VARCHAR(20),
    "street" VARCHAR(20),
    "country_code" INTEGER,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE "order"."customer_seq" 
AS INTEGER
START WITH 1 INCREMENT BY 50;
