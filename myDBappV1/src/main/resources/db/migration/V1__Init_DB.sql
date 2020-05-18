alter table if exists bonus drop constraint if exists FK9m8xlmsn1b53qlogoloee1hu5;
alter table if exists contract drop constraint if exists FKm8jvj0jm2ihmy0fvrupie0ndk;
alter table if exists guest drop constraint if exists FKlr8udswmerhm8l9dlp5h6nddo;
alter table if exists guest drop constraint if exists FKnosnorc0ag5x53jno0pu6fmxb;
alter table if exists guest drop constraint if exists FKphu54mjkksyb2p5as3jxsmuho;
alter table if exists guest drop constraint if exists FKjodw45n0ji6awsac21kl6g28x;
alter table if exists receipt drop constraint if exists FKjq3v0m6bmkmpe1rgieu3eut4u;
alter table if exists receipt drop constraint if exists FKohl1pelkitenhowb88b0tpbmr;
alter table if exists reservation drop constraint if exists FKc4g7ufacf1m8vmo4srcyhhx7y;
alter table if exists room drop constraint if exists FK4kmfw73x2vpfymk0ml875rh2q;
alter table if exists service drop constraint if exists FKher2uxwru83tqhxvv0ty52j6c;
drop table if exists bonus cascade;
drop table if exists building cascade;
drop table if exists client cascade;
drop table if exists company cascade;
drop table if exists contract cascade;
drop table if exists guest cascade;
drop table if exists receipt cascade;
drop table if exists reservation cascade;
drop table if exists room cascade;
drop table if exists service cascade;
    create table bonus (
        bonus_id int4 generated by default as identity,
        bonus varchar(255),
        contract_id int4,
        primary key (bonus_id));

    create table building (
        building_id int4 generated by default as identity,
        level int4 not null,
        primary key (building_id));

    create table client (
        client_id int4 generated by default as identity,
        client_name varchar(255),
        client_surname varchar(255),
        first_appearance date,
        primary key (client_id));

    create table company (
        company_id int4 generated by default as identity,
        company_name varchar(255),
        primary key (company_id));

    create table contract (
        contract_id int4 generated by default as identity,
        company_id int4,
        primary key (contract_id));

    create table guest (
        guest_id int4 generated by default as identity,
        guest_review varchar(255),
        review_type boolean not null,
        client_id int4,
        company_id int4,
        reservation_id int4,
        room_id int4,
        primary key (guest_id));

    create table receipt (
        receipt_id int4 generated by default as identity,
        guest_id int4, service_id int4,
        primary key (receipt_id));

    create table reservation (
        reservation_id int4 generated by default as identity,
        end_date date, start_date date,
        building_id int4,
        primary key (reservation_id));

    create table room (
        room_id int4 generated by default as identity,
        is_empty boolean not null,
        room_capacity int4 not null,
        room_current_guest_count int4 not null,
        room_floor int4 not null,
        room_orders_count int4 not null,
        room_profit int4 not null,
        building_id int4,
        primary key (room_id));

    create table service (
        service_id int4 generated by default as identity,
        service_name varchar(255),
        service_price int4 not null,
        building_id int4,
        primary key (service_id));

alter table if exists bonus add constraint FK9m8xlmsn1b53qlogoloee1hu5 foreign key (contract_id) references contract;
alter table if exists contract add constraint FKm8jvj0jm2ihmy0fvrupie0ndk foreign key (company_id) references company;
alter table if exists guest add constraint FKlr8udswmerhm8l9dlp5h6nddo foreign key (client_id) references client;
alter table if exists guest add constraint FKnosnorc0ag5x53jno0pu6fmxb foreign key (company_id) references company;
alter table if exists guest add constraint FKphu54mjkksyb2p5as3jxsmuho foreign key (reservation_id) references reservation;
alter table if exists guest add constraint FKjodw45n0ji6awsac21kl6g28x foreign key (room_id) references room;
alter table if exists receipt add constraint FKjq3v0m6bmkmpe1rgieu3eut4u foreign key (guest_id) references guest;
alter table if exists receipt add constraint FKohl1pelkitenhowb88b0tpbmr foreign key (service_id) references service;
alter table if exists reservation add constraint FKc4g7ufacf1m8vmo4srcyhhx7y foreign key (building_id) references building;
alter table if exists room add constraint FK4kmfw73x2vpfymk0ml875rh2q foreign key (building_id) references building;
alter table if exists service add constraint FKher2uxwru83tqhxvv0ty52j6c foreign key (building_id) references building;
