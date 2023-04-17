CREATE TABLE "order"."order"
(
    "id" INTEGER NOT NULL,
    "created_at" DATE NOT NULL,
    "updated_at" DATE,
    "status" VARCHAR(20),
    "customer_id" INTEGER,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE "order"."order_seq"
AS INTEGER
START WITH 1 INCREMENT BY 50;

ALTER TABLE "order"."order"
ADD CONSTRAINT "FK_order_customer_id"
FOREIGN KEY ("customer_id") REFERENCES "order"."customer";
