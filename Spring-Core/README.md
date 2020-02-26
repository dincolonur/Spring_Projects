## Spring-Core Project

Project's main class App. And in this class's main method first Load.loader() function is called.
In loader() function default data is loaded;

Events;
    Event{, id='33', name='Family Man', airDates=[2019-07-01T11:30, 2019-07-02T11:30, 2019-07-03T11:30, 2019-07-04T11:30, 2019-07-05T11:30], basePrice=10.0, rating=LOW, auditoriums={2019-07-01T11:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}, 2019-07-02T11:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-03T11:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-04T11:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}, 2019-07-05T11:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}}},
    Event{, id='22', name='Love Story', airDates=[2019-07-01T15:30, 2019-07-02T15:30, 2019-07-03T15:30, 2019-07-04T15:30, 2019-07-05T15:30], basePrice=12.0, rating=MID, auditoriums={2019-07-01T15:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-02T15:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-03T15:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-04T15:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}, 2019-07-05T15:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}}},
    Event{, id='11', name='Avengers', airDates=[2019-07-01T19:30, 2019-07-02T19:30, 2019-07-03T19:30, 2019-07-04T19:30, 2019-07-05T19:30], basePrice=15.0, rating=HIGH, auditoriums={2019-07-01T19:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}, 2019-07-02T19:30=Auditorium{name='Mercury', numberOfSeats=200, vipSeats=[65, 66, 67, 68, 55, 56, 57, 58, 75, 76, 77, 78]}, 2019-07-03T19:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-04T19:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}, 2019-07-05T19:30=Auditorium{name='Mars', numberOfSeats=300, vipSeats=[65, 66, 67, 68, 75, 44, 76, 77, 78, 55, 56, 88, 57, 58, 90, 91]}}}

Users;
    User{id='4', firstName='Hans', lastName='Gunter', email='hans@hans.com', tickets=[
            Ticket{id=4442, user=Hans, event=Love Story, seat=11},
            Ticket{id=4443, user=Hans, event=Family Man, seat=22},
            Ticket{id=4441, user=Hans, event=Avengers, seat=44}], birthday=1990-06-30},
    User{id='1', firstName='James', lastName='Darrel', email='james@james.com', tickets=[
            Ticket{id=1111, user=James, event=Avengers, seat=34},
            Ticket{id=1112, user=James, event=Avengers, seat=35},
            Ticket{id=1113, user=James, event=Love Story, seat=22},
            Ticket{id=1114, user=James, event=Love Story, seat=23}], birthday=1990-07-02},
    User{id='3', firstName='Tom', lastName='Hawyer', email='tom@tom.com', tickets=[
            Ticket{id=3331, user=Tom, event=Avengers, seat=77},
            Ticket{id=3332, user=Tom, event=Avengers, seat=78}], birthday=1990-07-01},
    User{id='2', firstName='Mike', lastName='Silver', email='mike@mike.com', tickets=[
            Ticket{id=2221, user=Mike, event=Avengers, seat=55},
            Ticket{id=2222, user=Mike, event=Love Story, seat=55},
            Ticket{id=2224, user=Mike, event=Family Man, seat=65},
            Ticket{id=2223, user=Mike, event=Love Story, seat=56}], birthday=1990-05-02}

Tickets;
    Ticket{id=2221, user=Mike, event=Avengers, seat=55},
    Ticket{id=2224, user=Mike, event=Family Man, seat=65},
    Ticket{id=1111, user=James, event=Avengers, seat=34},
    Ticket{id=1112, user=James, event=Avengers, seat=35},
    Ticket{id=1114, user=James, event=Love Story, seat=23},
    Ticket{id=2222, user=Mike, event=Love Story, seat=55},
    Ticket{id=2223, user=Mike, event=Love Story, seat=56},
    Ticket{id=1113, user=James, event=Love Story, seat=22},
    Ticket{id=4441, user=Hans, event=Avengers, seat=44},
    Ticket{id=4442, user=Hans, event=Love Story, seat=11},
    Ticket{id=3332, user=Tom, event=Avengers, seat=78},
    Ticket{id=4443, user=Hans, event=Family Man, seat=22},
    Ticket{id=3331, user=Tom, event=Avengers, seat=77}

Auditoriums;(from properties file)
    name=Mercury
    numberOfSeats=200
    vipSeats=55,56,57,58,65,66,67,68,75,76,77,78

    name2=Mars
    numberOfSeats2=300
    vipSeats2=44,55,56,57,58,65,66,67,68,75,76,77,78,88,90,91


Menu Usage:

     Please Select Menu Options:
    1 - User Operations
        ===============
         Please Select User Options:
        1 - Get All Users
        2 - Get User By Email Address
        3 - Get User By User ID
        4 - Remove User
        5 - Save User
        9 - To Upper Menu

    2 - Event Operations
        ===============
         Please Select Event Options:
        1 - Get All Events
        2 - Get Event By Name
        3 - Get Event By Event ID
        4 - Remove Event
        5 - Save Event
        9 - To Upper Menu

    3 - Booking Operations
        ===============
         Please Select Booking Options:
        1 - Get Tickets Price
        2 - Book Ticket
        3 - Get Purchased Tickets for Event
        4 - Get All Tickets
        9 - To Upper Menu

    9 - Exit

OD
