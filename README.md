# Room API Documation

## General Options For All End Points

**API URL** : `/room/**`

**Example Request Body** :
```json
{
"name": "111",
"password": "222"
}
```

**Data Constraints** : 
```json
name {
        Not Null,
        "min char" : 2,
        "max char"  : 35
}
password {
        Not null,
        "min char" : 2,
        "max char"  : 35
} 
```

**Auth required** : YES

**Auth method** : Basic Auth


**Error** : 


# End Points


## Create Room

Creates a new room and insert user in it

**URL** : `/create`

**Method**  : `POST`

**Response Body** :
```json
{
    "id": "5fd631b3a8c3c516d697e0a9",
    "name": "roomname",
    "password": "password",
    "users": [
        "username"
    ]
}
```

## Join Room

Inserts user to a room

**URL** : `/join`

**Method**  : `POST`

**Response Body** :
```json
{
    "id": "5fd64020a8c3c516d697e0ae",
    "name": "newroomname",
    "password": "password",
    "users": [
        "username"
    ]
}
```
## Get Users List Of Room

Returns users in the room

**URL** : `/user/list`

**Method**  : `POST`


**Response Body** :
```json

    [
        "user1",
        "user2",
        "user3"
    ]

```
## Delete Room

Delete room 

**URL** : `/delete`

**Method**  : `DELETE`

**Response Body** :
```json
{
    "id": "5fd64020a8c3c516d697e0ae",
    "name": "deletedroomname",
    "password": "123",
    "users": [
        "username1"
    ]
}

```

## Leave  Room

Removes user from room

**URL** : `/user/leave`

**Method**  : `DELETE`


**Response Body** :
```json
{
    "id": "5fd631b3a8c3c516d697e0a9",
    "name": "roomname",
    "password": "123",
    "users": [
        "user1"
    ]
}

```
