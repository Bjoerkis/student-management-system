#ADD STUDENT
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

#UPDATE STUDENT
http://localhost:8080/student-management-system/api/v1/students/update/id

Json body:
``` Json
{
"firstName": "Name",
"lastName": "Lastname",
"email": "email@something.com",
"phoneNumber": "123456789"
}
```

#FIND STUDENT(by Last name)
http://localhost:8080/student-management-system/api/v1/students/studentbylastname?lastName=Johnson
(Johnson är bara där som ett exempel, sätt in önskat namn vid din sökning)

#FIND STUDENT(by ID)
http://localhost:8080/student-management-system/api/v1/students/1
(id är bara 1 som ett exempel, sätt in önskat id vid din sökning)

#DELETE STUDENT(by ID)
http://localhost:8080/student-management-system/api/v1/students/1
(id är bara 1 som ett exempel, sätt in önskat id vid din sökning)