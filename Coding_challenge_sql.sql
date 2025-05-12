create database petpalsdb;

use petpalsdb;
-- pets table
create table pets (
    id int auto_increment primary key,
    name varchar(50),
    age int,
    breed varchar(50),
    pet_type varchar(10), 
    extra_info varchar(50) -
);

-- donation table
create table donations (
    id int auto_increment primary key,
    donor_name varchar(100),
    amount decimal(10,2),
    donation_type varchar(20), -- 'cash' or 'item'
    donation_date date,
    item_type varchar(50)
);

-- adoption event table
create table adoption_events (
    id int auto_increment primary key,
    event_name varchar(100),
    event_date date
);

-- participants table
create table participants (
    id int auto_increment primary key,
    participant_name varchar(100),
    event_id int,
    foreign key (event_id) references adoption_events(id)
);

insert into adoption_events (id, event_name, event_date) values
(101, 'summer pet adoption fair', '2025-06-01'),
(102, 'paws & hearts weekend', '2025-06-15'),
(103, 'adopt-a-pet marathon', '2025-07-05'),
(104, 'furry friends fest', '2025-07-20'),
(105, 'home for every tail', '2025-08-10');

