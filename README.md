# Master Cloud Apps

## Practica 1

### REST API


#### POST /api/posts

**Body:**   
```
{
"title": "post title1",
"body": "post body"
}
```
**Response:**  
```
{"id":1,"title":"post title1","body":"post body","comments":[]}
```

#### GET /api/posts
**Response:**
```
[{"id":1,"title":"post title1"}]
```
    
#### GET /api/posts/_{{postId}}_
**Response:**

```
{"id":1,"title":"post title1","body":"post body","comments":[{"id":2,"user":"comment user1","body":"comment body"}]}
```


#### POST /api/posts/_{{postId}}_/comments
**Body:**
```
{
"user": "comment user1",
"body": "comment body"
}
```
**Response:**
```
{"id":2,"user":"comment user1","body":"comment body"}
```

#### GET /api/posts/_{{postId}}_/comments
**Response:**

```
[{"id":2,"user":"comment user1","body":"comment body"}]
```

#### DELETE /api/posts/_{{postId}}_/comments/_{{commentId}}_
**Response:**

```
[{"id":2,"user":"comment user1","body":"comment body"}]
```

