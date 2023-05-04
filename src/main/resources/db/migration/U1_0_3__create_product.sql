ALTER TABLE "order"."product"
DROP CONSTRAINT "FK_product_category_id";

DROP SEQUENCE "order"."product_seq"; 

DROP TABLE "order"."product";