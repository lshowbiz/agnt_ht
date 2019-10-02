-- drop the existing database
drop database jecs;

-- create the test user
create user test password 'test';

-- create the database
create database jecs owner test;
