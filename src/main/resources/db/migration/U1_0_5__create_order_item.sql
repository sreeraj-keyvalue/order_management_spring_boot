ALTER TABLE "order"."order_item"
DROP CONSTRAINT "FK_order_item_order_id";

ALTER TABLE "order"."order_item"
DROP CONSTRAINT "FK_order_item_product_id";

DROP SEQUENCE "order"."order_item_seq";

DROP TABLE "order"."order_item";