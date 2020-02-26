## Spring-Simple-DB Project

Project's main class App. User, Event, Ticket information loaded to derby embedded database.

create_tables.sql
insert_tables.sql

run as initial setup.

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


Best Regards

Onur Dincol