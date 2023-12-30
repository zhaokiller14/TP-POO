# University Management System

The University Management System is a simple object-oriented programming (OOP) Java mini-project designed to showcase the implementation of various classes and their interactions within a university setting. The project includes classes representing different entities such as people, courses, clubs, groups, classrooms, and events. It allows for the management of students, teachers, courses, clubs, and events within a university environment.

## 1. Person Class:
   - Represents a generic person with attributes such as name and age.

## 2. Student Class
   - Inherits from the Person class and includes additional attributes like a student ID and a list of enrolled courses.
   - Provides methods for enrolling in courses, participating in events, and joining clubs.

## 3. Teacher Class
   - Inherits from the Person class and includes additional attributes like a teacher ID and a list of assigned subjects.
   - Provides a method for assigning subjects to teachers.

## 4. AdministrationEmployee Class
   - Inherits from the Person class and represents administrative staff with an employee ID.

## 5. Subject Class
   - Represents a subject with attributes such as a subject code and name.

## 6. Classroom Class
   - Represents a classroom with attributes like room number and size.

## 7. Group Class
   - Represents a group of students with a group name.
   - Allows for adding and displaying students within the group.

## 8. Club Class
   - Represents a club with a club name and a list of members.
   - Provides methods for adding members, checking membership, and displaying club members.

## 9. Course Class
   - Represents a course with attributes like a course code, a list of subjects, a teacher, a classroom, and a group.
   - Provides methods for adding subjects, setting a teacher, classroom, and group, and displaying course details.

## 10. Event Class
   - Represents an event with attributes like an event name, date, participants, an organizer (club), and participation fees.
   - Provides methods for adding participants, managing participation fees based on membership, and displaying participants.

## 11. Main Class
   - Contains the main method to demonstrate the functionality of the university management system.
   - Creates instances of the classes, enrolls students in courses, adds members to clubs, forms groups, and organizes events.

This mini-project simulates a simplified university management system where students can enroll in courses, join clubs, participate in events, and be part of groups. It also demonstrates the relationship between teachers, subjects, and courses, showcasing the basic functionalities of an OOP-based university management system.