insert into "order"."customer" 
(
	"id", created_at, "name", "phone", "email", "street", "city", "state", "profile_pic" 
)
values (
	1, now(),'Raju', '123456789', 'raju@abc.com', 'pq', 'rs', 'tu', 'ab/cd/a.jpg'
);

insert into "order"."customer" 
(
	"id", created_at, "name", "phone", "email", "street", "city", "state", "profile_pic" 
)
values (
	2, now(),'Jose', '321654987', 'jose@abc.com', 'lm', 'no', 'qw', 'mn/cd/a.jpg'
);

insert into "order".category  
(
	"id", created_at, "name", parent_category  
)
values (
	1, now(), 'FMCG', null
);

insert into "order".category  
(
	"id", created_at, "name", parent_category  
)
values (
	2, now(), 'SOFT DRINK', 1
);

insert into "order".category  
(
	"id", created_at, "name", parent_category  
)
values (
	3, now(), 'CARBONATED DRINK', 2
);

insert into "order".product  
(
	"id", created_at, "name", "image", "description", "unit_price", "category_id"  
)
values (
	1, now(), 'pepsi', '/pepsi/1.png', 'Pepsi', 60, 3
);

insert into "order".product  
(
	"id", created_at, "name", "image", "description", "unit_price", "category_id"  
)
values (
	2, now(), 'Maggi', '/maggi/1.png', 'Maggi', 25, 1
);
