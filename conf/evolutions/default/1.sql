# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table city (
  id                        bigint not null,
  insee_code                varchar(255),
  zip_code                  varchar(255),
  name                      varchar(255),
  region_id                 bigint,
  department_id             bigint,
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint pk_city primary key (id))
;

create table cooking_speciality (
  id                        bigint not null,
  name                      varchar(255),
  nature                    integer,
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint ck_cooking_speciality_nature check (nature in (0,1)),
  constraint pk_cooking_speciality primary key (id))
;

create table department (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  region_id                 bigint,
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint pk_department primary key (id))
;

create table history (
  id                        bigint not null,
  table_name                varchar(10),
  table_operatio_type       varchar(6),
  line_key                  bigint,
  line_value                varchar(1000),
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint ck_history_table_name check (table_name in ('RESTAURANT')),
  constraint ck_history_table_operatio_type check (table_operatio_type in ('CREATE','UPDATE','DELETE')),
  constraint pk_history primary key (id))
;

create table region (
  id                        bigint not null,
  code                      varchar(255),
  name                      varchar(255),
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint pk_region primary key (id))
;

create table restaurant (
  id                        bigint not null,
  name                      varchar(255),
  web_site                  varchar(255),
  description               varchar(500),
  is_credit_card_accepted   boolean,
  is_restaurant_ticket_accepted boolean,
  is_delivery_possible      boolean,
  is_takeaway               boolean,
  is_online_booking         boolean,
  is_prayer_room            boolean,
  is_establishment_certified boolean,
  is_amenagment_handicapped boolean,
  last_verification         timestamp,
  address                   varchar(255),
  city_id                   bigint,
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint pk_restaurant primary key (id))
;


create table restaurant_cooking_speciality (
  cooking_speciality_id          bigint not null,
  restaurant_id                  bigint not null,
  constraint pk_restaurant_cooking_speciality primary key (cooking_speciality_id, restaurant_id))
;
create sequence city_seq;

create sequence cooking_speciality_seq;

create sequence department_seq;

create sequence history_seq;

create sequence region_seq;

create sequence restaurant_seq;

alter table city add constraint fk_city_region_1 foreign key (region_id) references region (id) on delete restrict on update restrict;
create index ix_city_region_1 on city (region_id);
alter table city add constraint fk_city_department_2 foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_city_department_2 on city (department_id);
alter table department add constraint fk_department_region_3 foreign key (region_id) references region (id) on delete restrict on update restrict;
create index ix_department_region_3 on department (region_id);
alter table restaurant add constraint fk_restaurant_city_4 foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_restaurant_city_4 on restaurant (city_id);



alter table restaurant_cooking_speciality add constraint fk_restaurant_cooking_special_01 foreign key (cooking_speciality_id) references cooking_speciality (id) on delete restrict on update restrict;

alter table restaurant_cooking_speciality add constraint fk_restaurant_cooking_special_02 foreign key (restaurant_id) references restaurant (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists city;

drop table if exists cooking_speciality;

drop table if exists restaurant_cooking_speciality;

drop table if exists department;

drop table if exists history;

drop table if exists region;

drop table if exists restaurant;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists city_seq;

drop sequence if exists cooking_speciality_seq;

drop sequence if exists department_seq;

drop sequence if exists history_seq;

drop sequence if exists region_seq;

drop sequence if exists restaurant_seq;

