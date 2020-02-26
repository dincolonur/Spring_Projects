CREATE TABLE events (
  id  INTEGER PRIMARY KEY,
  name VARCHAR(30),
  airDates  VARCHAR(500),
  basePrice VARCHAR(30),
  rating VARCHAR(30),
  auditoriums VARCHAR(1000)
);

CREATE TABLE users (
  id  INTEGER PRIMARY KEY,
  firstName VARCHAR(30),
  lastName VARCHAR(30),
  email VARCHAR(30),
  tickets VARCHAR(1000),
  birthday VARCHAR(30)
);

CREATE TABLE tickets (
    id  INTEGER PRIMARY KEY,
    userID INTEGER,
    eventID INTEGER,
    eventDateTime VARCHAR(30),
    seat INTEGER
);

CREATE TABLE event_counters (
    eventID INTEGER PRIMARY KEY,
    callByNameCounter INTEGER,
    priceQueriedCounter INTEGER,
    ticketsBookedCounter INTEGER
);

CREATE TABLE user_discount (
    userID INTEGER PRIMARY KEY,
    totalDiscount INTEGER
);