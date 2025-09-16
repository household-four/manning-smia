INSERT INTO Organization (id, name, category, established_date) VALUES
(1, 'Pickleball League', 'SPORTS', '2000-01-01'),
(2, 'Fitness Club', 'FITNESS', '2010-05-15'),
(3, 'Art Lovers', 'ARTS', '2015-09-20');

INSERT INTO Person (id, name, major, department, date_of_birth, phone, email) VALUES
(1, 'John Doe', 'Computer Science', 'Engineering', '1990-01-01', '1234567890', 'john.doe@example.com'),
(2, 'Jane Smith', 'Biology', 'Science', '1992-02-02', '0987654321', 'jane.smith@example.com'),
(3, 'Alice Johnson', 'Fine Arts', 'Arts', '1995-03-03', '1122334455', 'alice.johnson@example.com'),
(4, 'Michael Brown', 'Business Administration', 'Management', '1988-04-04', '2233445566', 'michael.brown@example.com'),
(5, 'Emily Davis', 'Kinesiology', 'Health Sciences', '1991-05-05', '3344556677', 'emily.davis@example.com'),
(6, 'David Wilson', 'History', 'Humanities', '1989-06-06', '4455667788', 'david.wilson@example.com'),
(7, 'Sophia Martinez', 'Literature', 'Arts', '1993-07-07', '5566778899', 'sophia.martinez@example.com'),
(8, 'Daniel Taylor', 'Mechanical Engineering', 'Engineering', '1994-08-08', '6677889900', 'daniel.taylor@example.com');

INSERT INTO person_organization (person_id, organization_id) VALUES
(1, 1),
(2, 1), 
(3, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 3);

UPDATE Organization SET president_id = 1 WHERE id = 1;
UPDATE Organization SET president_id = 4 WHERE id = 2;
UPDATE Organization SET president_id = 7 WHERE id = 3;