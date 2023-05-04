ALTER TABLE "order"."order"
DROP CONSTRAINT "FK_order_customer_id";

DROP SEQUENCE "order"."order_seq";

DROP TABLE "order"."order";