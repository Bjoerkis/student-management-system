# ADD STUDENT
http://localhost:8080/student-management-system/api/v1/students/new

Json body:
``` Json
{
"firstName": "Name",
"lastName": "Lastname",
"email": "email@something.com",
"phoneNumber": "123456789"
}
```

# UPDATE STUDENT
http://localhost:8080/student-management-system/api/v1/students/update/id

*Lägg till det ID på den student som du önskar ändra*

Json body:
``` Json
{
"firstName": "Name",
"lastName": "Lastname",
"email": "email@something.com",
"phoneNumber": "123456789"
}
```

# FIND STUDENT(by Last name)
http://localhost:8080/student-management-system/api/v1/students/studentbylastname?lastName=Johnson

*(Johnson är bara där som ett exempel, sätt in önskat namn vid din sökning)*

# FIND STUDENT(by ID)
http://localhost:8080/student-management-system/api/v1/students/1

*(id är bara 1 som ett exempel, sätt in önskat id vid din sökning)*

# DELETE STUDENT(by ID)
http://localhost:8080/student-management-system/api/v1/students/1

*(id är bara 1 som ett exempel, sätt in önskat id vid din sökning)*

# ADD SUBJECT
http://localhost:8080/student-management-system/api/v1/subjects

Json Body:
``` Json
{
"subject": "English"
}
```
# GET ALL INFO FROM SUBJECT
http://localhost:8080/student-management-system/api/v1/subjects/search?subjectid=1

*(id är bara 1 som ett exempel, sätt in önskat id)*

# ADD TEACHER
http://localhost:8080/student-management-system/api/v1/teachers

Json Body:
``` Json
{
"name": "Bruce"
}
```

# STUDENT TO SUBJECT
http://localhost:8080/student-management-system/api/v1/subjects/addstudent?subjectid=1&studentid=4

*(id är bara 1 och 4 är bara ett exempel, sätt in önskat id)*

# TEACHER TO SUBJECT 
http://localhost:8080/student-management-system/api/v1/subjects/addteacher?subjectid=1&teacherid=5

*(id är bara 1 och 5 är bara ett exempel, sätt in önskat id)*

# DELETE SUBJECT 
http://localhost:8080/student-management-system/api/v1/subjects/1

*(id är bara 1 som ett exempel, sätt in önskat id)*

# DELETE TEACHER
http://localhost:8080/student-management-system/api/v1/teachers/1

*(id är bara 1 som ett exempel, sätt in önskat id)*

# REMOVE STUDENT FROM SUBJECT
http://localhost:8080/student-management-system/api/v1/subjects/removestudent?subjectid=1&studentid=5

*(id är bara 1 och 5 är bara ett exempel, sätt in önskat id)*

# REMOVE TEACHER FROM SUBJECT
http://localhost:8080/student-management-system/api/v1/subjects/removeteacher?subjectid=1&teacherid=2

*(id är bara 1 och 2 är bara ett exempel, sätt in önskat id)*