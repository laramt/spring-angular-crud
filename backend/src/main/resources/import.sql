INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Calculus I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Sarah Doux', '2023-02-09', '2023-05-21');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Calculus II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Mandy Nouf', '2023-02-13', '2023-05-28');

INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('John Smith', 'john.smith@example.com', '1998-05-10', '555-1234', 'Computer Science');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Emma Johnson', 'emma.johnson@example.com', '1999-08-15', '555-5678', 'Business Administration');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Michael Brown', 'michael.brown@example.com', '1997-11-20', '555-9012', 'Engineering');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Sarah Davis', 'sarah.davis@example.com', '1996-03-25', '555-3456', 'Psychology');

INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 3);
INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 4);
INSERT INTO course_student_tb(course_id, student_id) VALUES(2, 1);
INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 1);
INSERT INTO course_student_tb(course_id, student_id) VALUES(2, 2);