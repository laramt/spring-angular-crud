INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Calculus I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Sarah Doux', '2023-02-09', '2023-05-21');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Calculus II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Mandy Nouf', '2023-02-13', '2023-05-28');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Physics I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'John Smith', '2023-02-15', '2023-05-25');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Physics II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Emma Johnson', '2023-02-18', '2023-05-30');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Computer Science I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Michael Brown', '2023-02-20', '2023-05-22');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Computer Science II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Sarah Davis', '2023-02-23', '2023-05-24');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Chemistry I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'David Johnson', '2023-02-25', '2023-05-26');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('Chemistry II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Amy Smith', '2023-02-28', '2023-05-27');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('English I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Robert Davis', '2023-03-03', '2023-06-02');
INSERT INTO course_tb (name, description, professor, start_date, end_date) VALUES ('English II', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', 'Laura Brown', '2023-03-06', '2023-06-05');

INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('John Smith', 'john.smith@example.com', '1998-05-10', '555-1234', 'Computer Science');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Emma Johnson', 'emma.johnson@example.com', '1999-08-15', '555-5678', 'Business Administration');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Michael Brown', 'michael.brown@example.com', '1997-11-20', '555-9012', 'Engineering');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('Sarah Davis', 'sarah.davis@example.com', '1996-03-25', '555-3456', 'Psychology');
INSERT INTO student_tb(name, email, birth_date, phone_number, major) VALUES('David Johnson', 'david.johnson@example.com', '1995-07-30', '555-7890', 'Chemistry');

INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 3);
INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 4);
INSERT INTO course_student_tb(course_id, student_id) VALUES(2, 1);
INSERT INTO course_student_tb(course_id, student_id) VALUES(1, 1);
INSERT INTO course_student_tb(course_id, student_id) VALUES(2, 2);
INSERT INTO course_student_tb(course_id, student_id) VALUES(3, 1);
INSERT INTO course_student_tb(course_id, student_id) VALUES(4, 2);
INSERT INTO course_student_tb(course_id, student_id) VALUES(5, 3);
INSERT INTO course_student_tb(course_id, student_id) VALUES(6, 4);
INSERT INTO course_student_tb(course_id, student_id) VALUES(7, 5);