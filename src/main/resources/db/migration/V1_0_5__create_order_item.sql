CREATE TABLE "order"."order_item"
(
    "id" INTEGER NOT NULL,
    "created_at" DATE NOT NULL,
    "updated_at" DATE,
    "quantity" INTEGER CHECK ("quantity">=1),
    "order_id" INTEGER,
    "product_id" INTEGER,
    PRIMARY KEY ("id")
);

CREATE SEQUENCE "order"."order_item_seq"
AS INTEGER
START WITH 1 INCREMENT BY 50;

ALTER TABLE "order"."order_item"
ADD CONSTRAINT "FK_order_item_order_id"
FOREIGN KEY ("order_id") REFERENCES "order"."order";

ALTER TABLE "order"."order_item"
ADD CONSTRAINT "FK_order_item_product_id"
FOREIGN KEY ("product_id") REFERENCES "order"."product";
