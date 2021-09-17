# CRM system for managing
A simple implementation of CRM system.

### Technology stack:

- Spring boot;
- Spring security;
- Spring web;
- Spring data JPA;
- Spring mail;
- Spring Data Criteria
- JWT;
- Liquibase;
- PostgreSQL as a main DB (H2 as a test database);
- Docker-compose;
- Hibernate;
- Spring validation;
- Mapstruct;
- Jackson;
- Lombok;

### Configuration:
To start a database image from docker-compose write into terminal:
```
$ docker-compose up -d
```
To remove the image:
```
$ docker-compose down
```

### Available features:

#### *Version 0.1.0*
- Contact managing (list of all contacts, registered into the system);
- To Do list (managing tasks of a user);
- Send reports as a user and managing them as a manager;
- Dashboard;
- Registration;
- Profile managing:
    - Get contact details;
    - Edit contact information;
    - Change user data (email and password);
  
### Endpoints:

#### *Registration and auth*:

- Registration a new user
```
POST /login/registration/common
```
- Authentication
```
POST /login/auth
```
- Refresh user's token
```
POST /login/refresh
```
- Forget password request
```
POST /
```
- Reset token validation
```
GET /reset?token=*
```
- Reset password
```
PUT /reset
```
#### *Dashboard*:
- Show dashboard
```
GET /manage/dashboard
```
#### *Profile*:
- Show user's profile
```
GET /manage/profile
```
- Update a contact card
```
PUT /manage/profile/contact
```
- Update user's email
```
PUT /manage/profile/main/email
```
- Update user's password
```
PUT /manage/profile/main/password?email=*
```
#### *Contact manager*:
- Get contacts
```
GET /manage/contacts (pageable available)
```
- Get contact's details
```
GET /manage/contacts/contact/{contactId}
```
- Get a contact by filtering params
```
GET /manage/contacts?filteredBy=*fieldName*&query=* (pageable available)
```
- Get a contact by its email
```
GET /manage/contacts/contact?email=*
```
#### *TO DO list*:
- Create or get already exist TO DO list:
```
POST /manage/todo
```
- Save a new task
```
POST /manage/todo/tasks/task
```
- Update a task
```
PUT /manage/todo/tasks/task?id={taskId}
```
- Get a task by id
```
GET /manage/todo/tasks/task/{taskId}
```
- Delete a task by id
```
DELETE /manage/todo/tasks/task?id={taskId}
```
- Get tasks (pageable available)
```
GET /manage/todo/tasks
```
- Get active user's tasks (pageable available)
```
GET /manage/todo/tasks/active
```
- Get all tasks start from a date (pageable available)
```
GET /manage/todo/tasks/{date}
```
- Get all missed tasks (pageable available)
```
GET /manage/todo/tasks/missed
```
- Get all tasks between dates (pageable available)
```
GET /manage/todo/tasks/between?start={startDate}&end={endDate}
```
- Get all filtered by params tasks
```
GET /manage/todo/tasks?filters={filterFields}&query=*&sort=*
```
### *Reports:*
####[User]:
- Create a new report
```
POST /manage/reports/report
```
- Update a report
```
PUT /manage/reports/report?id={reportId}
```
- Delete a report by id
```
DELETE /manage/reports/report?id={reportId}
```
- Get report's details
```
GET /manage/reports/report/{reportId}
```
- Get all reports
```
GET /manage/reports
```
- Get all reports by a topic
```
GET /manage/reports/topic?topic=*
```
- Get all reports by a status
```
GET /manage/reports/status?status=* 
```
#### [MANAGER]
- Update user's report
```
PUT /manage/manager/reports/report?id={reportId}
```
- Get a report's details
```
GET /manage/manager/reports/report/{reportId}
```
- Delete a report by id
```
DELETE /manage/manager/reports/report?id={reportId}
```
- Get all users reports
```
GET /manage/manager/reports
```
- Get all today's reports
```
GET /manage/manager/reports/today
```
- Get all reports by a date
```
GET /manage/manager/reports/{date}
```

    

