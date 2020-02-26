insert into events values(11, 'Avengers', '2019-07-01T19:30,2019-07-02T19:30,2019-07-03T19:30,2019-07-04T19:30,2019-07-05T19:30', '15.0', 'HIGH', '2019-07-01T19:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78|2019-07-02T19:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78|2019-07-03T19:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-04T19:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-05T19:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91');
insert into events values(22, 'Love Story', '2019-07-01T15:30,2019-07-02T15:30,2019-07-03T15:30,2019-07-04T15:30,2019-07-05T15:30', '12.0', 'MID', '2019-07-01T15:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-02T15:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-03T15:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-04T15:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78|2019-07-05T15:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78');
insert into events values(33, 'Family Man', '2019-07-01T11:30,2019-07-02T11:30,2019-07-03T11:30,2019-07-04T11:30,2019-07-05T11:30', '10.0', 'LOW', '2019-07-01T11:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78|2019-07-02T11:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-03T11:30;Mars;300;65-66-67-68-75-44-76-77-78-55-56-88-57-58-90-91|2019-07-04T11:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78|2019-07-05T11:30;Mercury;200;65-66-67-68-55-56-57-58-75-76-77-78');

insert into tickets values(1111,1,11,'2019-07-01T19:30',34);
insert into tickets values(1112,1,11,'2019-07-01T19:30',35);
insert into tickets values(1113,1,22,'2019-07-02T15:30',22);
insert into tickets values(1114,1,22,'2019-07-02T15:30',23);
insert into tickets values(2221,2,11,'2019-07-02T19:30',55);
insert into tickets values(2222,2,22,'2019-07-03T15:30',55);
insert into tickets values(2223,2,22,'2019-07-04T15:30',56);
insert into tickets values(2224,2,33,'2019-07-04T11:30',65);
insert into tickets values(3331,3,11,'2019-07-05T19:30',77);
insert into tickets values(3332,3,11,'2019-07-05T19:30',78);
insert into tickets values(4441,4,11,'2019-07-03T19:30',44);
insert into tickets values(4442,4,22,'2019-07-01T15:30',11);
insert into tickets values(4443,4,33,'2019-07-02T11:30',22);

insert into users values(1,'James','Darrel','james@james.com','1111;1112;1113;1114','1990-07-02');
insert into users values(2,'Mike','Silver','mike@mike.com','2221;2222;2223;2224','1990-05-02');
insert into users values(3,'Tom','Hawyer','tom@tom.com','3331;3332','1990-07-01');
insert into users values(4,'Hans','Gunter','hans@hans.com','4441;4442;4443','1990-06-30');

insert into event_counters values(11,0,0,0);
insert into event_counters values(22,0,0,0);
insert into event_counters values(33,0,0,0);

insert into user_discount values(1,0);
insert into user_discount values(2,0);
insert into user_discount values(3,0);
insert into user_discount values(4,0);




