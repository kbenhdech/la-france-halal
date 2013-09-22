# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  constraint pk_restaurant primary key (id))
;

create sequence restaurant_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists restaurant;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists restaurant_seq;

