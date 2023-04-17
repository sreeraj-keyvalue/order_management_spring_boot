CREATE TABLE "order"."category"
(
    "id" INTEGER NOT NULL, 
    "created_at" DATE NOT NULL,
    "updated_at" DATE,
    "name" VARCHAR(50) NOT NULL,
    "parent_category" INTEGER,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE "order"."category_seq"
AS INTEGER
START WITH 1 INCREMENT BY 50;
