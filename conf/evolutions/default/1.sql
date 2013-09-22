# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table restaurant (
  id                        bigint not null,
  nom                       varchar(255),
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
  created_at                timestamp not null,
  updated_at                timestamp not null,
  constraint pk_restaurant primary key (id))
;

create sequence history_seq;

create sequence restaurant_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists history;

drop table if exists restaurant;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists history_seq;

drop sequence if exists restaurant_seq;

