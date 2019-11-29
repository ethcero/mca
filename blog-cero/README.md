# Master Cloud Apps

## Practica 1

### REST API


#### POST /api/posts

Body:   
```
{
"title": "post title1",
"body": "post body"
}
```
Response:  
```
{"id":1,"title":"post title1","body":"post body","comments":[]}
```

#### GET /api/posts
Response:
```
[{"id":1,"title":"post title1"}]
```
    
#### GET /api/posts/_{{postId}}_
Response:

```
{"id":1,"title":"post title1","body":"post body","comments":[{"id":3,"author":{"id":2,"name":"pepito"},"postId":1,"body":"comment bodys"}]}
```


#### POST /api/posts/_{{postId}}_/comments
Body:
```
{
"authorId": 2,
"body": "comment bodys"
}
```
Response:
```
{"id":3,"author":{"id":2,"name":"pepito","age":23},"postId":1,"body":"comment bodys"}
```

#### GET /api/posts/_{{postId}}_/comments
Response:

```
[{"id":3,"author":{"id":2,"name":"pepito"},"postId":1,"body":"comment bodys"}]
```

#### DELETE /api/posts/_{{postId}}_/comments/_{{commentId}}_
Response:

```
[{"id":2,"user":"comment user1","body":"comment body"}]
```

#### POST /api/authors

Body:   
```
{
"name": "pepito",
"age": 23
}
```
Response:  
```
{"id":2,"name":"pepito","age":23}
```

#### GET /api/authors/_{{authorId}}_/comments
Response:

```
[{"id":3,"postId":1,"body":"comment bodys"}]
```
