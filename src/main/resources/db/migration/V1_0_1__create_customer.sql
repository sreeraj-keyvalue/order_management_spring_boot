create table "order".customer (id integer not null, created_at date, updated_at date, city varchar(255), email varchar(255), name varchar(255), phone varchar(255), profile_pic varchar(255), state varchar(255), street varchar(255), primary key (id));
create sequence customer_seq start with 1 increment by 50;
