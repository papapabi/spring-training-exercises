drop table T_ACCOUNT_CARD if exists;
drop table T_ACCOUNT if exists;
drop table T_MERCHANT if exists;
drop table T_REWARD if exists;
drop sequence S_REWARD_CONFIRMATION_NUMBER if exists;
drop table DUAL_REWARD_CONFIRMATION_NUMBER if exists;

create table T_ACCOUNT (ID integer identity primary key, NUMBER varchar(9), NAME varchar(50) not null, TOTAL_POINTS decimal not null, unique(NUMBER));
create table T_ACCOUNT_CARD (ID integer identity primary key, ACCOUNT_ID integer, NUMBER varchar(16), unique(ACCOUNT_ID, NUMBER));
create table T_MERCHANT (ID integer identity primary key, NUMBER varchar(10) not null, NAME varchar(80) not null, AMOUNT_PER_POINT double not null, MINIMUM_PURCHASE_AMOUNT double not null, unique(NUMBER));
create table T_REWARD (ID integer identity primary key, CONFIRMATION_NUMBER varchar(25) not null, REWARD_POINTS double not null, REWARD_DATE date not null, ACCOUNT_NUMBER varchar(9) not null, CARD_NUMBER varchar(16), MERCHANT_NUMBER varchar(10) not null, PURCHASE_AMOUNT double not null, PURCHASE_DATE date not null, unique(CONFIRMATION_NUMBER));

create sequence S_REWARD_CONFIRMATION_NUMBER start with 1;
create table DUAL_REWARD_CONFIRMATION_NUMBER (ZERO integer);
insert into DUAL_REWARD_CONFIRMATION_NUMBER values (0);

alter table T_ACCOUNT_CARD add constraint FK_ACCOUNT_CARD foreign key (ACCOUNT_ID) references T_ACCOUNT(ID) on delete cascade;
