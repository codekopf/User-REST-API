# User API design

This is a simple REST API for user management.

## Features
* Simple authorization
* H2 in-memory database implemented
* CRUD operation upon user
* Sorting user according the surname ascending
* User contains basic information and UUID of its creator
* List of users and user details are available to all roles
* Creating, updating and deleting users available only for ADMIN role, but it is based on permissions which can share multiple roles 
* User can have more roles
* Unit tests
* Integration tests cover partially controller and repository


## Dev notes
* All users in seed file are created by admin
* Methods constrains are set for fixed roles padded to authorized users during the application start
* User roles are mapped in separate table


## Ideas for improvement
* Fix TODOs
* Improve roles and permissions 
* Change simple auth for token auth
* Use PostgreSQL DB for migrations and better integration tests
* Check if createBy id exists for creating new user
* Hashing password
* Implement object assertion and custom class validation, checkers


## Rest endpoints
Please fill up Postman with following credentials for Basic Auth for ADMIN role: 

* Username: John 
* Password: test

And for USER role :

* Username: Oliver 
* Password: test


### All Users
* GET http://localhost:8443/api/v1/users/


### Single User
* GET http://localhost:8443/api/v1/users/51a7dda1-4984-43d4-b625-959a0b3e


### Create User
* POST http://localhost:8443/api/v1/users/ 
* e.g JSON padding data:
```json
    {
        "firstName": "Bridget",
        "lastName": "Jones",
        "email": "bridget.jones@gmail.com",
        "phone": "000000000",
        "password": "test",
        "roles": [
                    "MODERATOR",
                    "USER"
                ],
        "createdBy": "51a7dda1-4984-43d4-b625-0000959a0b3e"
    }
```


### Update User
* PUT http://localhost:8443/api/v1/users/5bdc25fc-b9a5-4f4a-b4b8-73357056c8cf
* e.g JSON padding data:
```json
    {
        "firstName": "Bridget",
        "lastName": "Jones",
        "email": "bridget.jones@gmail.com",
        "phone": "000000000",
        "password": "test",
        "roles": [
                    "MODERATOR",
                    "USER"
                ],
        "createdBy": "51a7dda1-4984-43d4-b625-0000959a0b3e"
    }
```
* ID belongs to John Average


### Delete User
* DELETE http://localhost:8443/api/v1/users/5bdc25fc-b9a5-4f4a-b4b8-73357056c8cf

