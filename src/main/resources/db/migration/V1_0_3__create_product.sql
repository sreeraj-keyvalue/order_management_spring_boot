CREATE TABLE "order"."product"
(
    "id" INTEGER NOT NULL,
    "created_at" DATE NOT NULL,
    "updated_at" DATE,
    "name" VARCHAR(50) NOT NULL,
    "category_id" INTEGER NOT NULL,
    "image" VARCHAR(255),
    "description" VARCHAR(255),
    "unit_price" NUMERIC,
    primary key ("id")
);

CREATE SEQUENCE "order"."product_seq" 
AS INTEGER
START WITH 1 INCREMENT BY 50;

ALTER TABLE "order"."product"
ADD CONSTRAINT "FK_product_category_id"
FOREIGN KEY ("category_id") REFERENCES "order"."category";
