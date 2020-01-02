package es.codeurjc.daw.restassured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import es.codeurjc.daw.Comment;
import es.codeurjc.daw.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

/**
 * @author fran
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogRestControllerTest {

    @LocalServerPort
    int port;

    Response postResponse;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        postResponse = createFakePost();
    }

    @Test
    public void givenPostWhenCreateNewPostThenReturnTheCreatedPost(){

       postResponse.
                then().
                statusCode(201).
                contentType(ContentType.JSON).
                body("id", notNullValue()).
                body("title", equalTo("testTitle"));

       Response getPostResponse = getFakePost();
       getPostResponse.then().statusCode(200);

    }

    @Test
    public void givenPostWhenCreateNewCommentThenReturnTheCreatedComment(){

        Response commentResponse = createFakeComment();
        commentResponse.
                then().
                statusCode(201).
                contentType(ContentType.JSON).
                body("id", notNullValue()).
                body("author", equalTo("authorTest")).
                body("message", equalTo("messagetest"));

        Response getPostResponse = getFakePost();

        getPostResponse.then().
                body("comments", not(empty()));

    }

    @Test
    public void givenPostWithCommentsWhenDeleteCommentThenCommentDeleted(){

        int postId = from(postResponse.getBody().asString()).get("id");

        Response commentResponse = createFakeComment();
        int commentId = from(commentResponse.getBody().asString()).get("id");

        given().
            request()
                .delete("/api/post/" + postId + "/comment/" + commentId)
                .then().statusCode(204);

        Response getPostResponse = getFakePost();
        getPostResponse.then().
                body("comments",empty());
    }


    private Response createFakePost(){
        Post expectedPost = new Post("testTitle", "testContent");

        return given().
                body(expectedPost).
                contentType(ContentType.JSON).
                when().
                post("/api/post").
                thenReturn();
    }

    private Response createFakeComment() {
        int postId = from(postResponse.getBody().asString()).get("id");

        Comment expectedComment = new Comment("authorTest", "messagetest");

        return given().
                body(expectedComment).
                contentType(ContentType.JSON).
                when().
                post("/api/post/" + postId + "/comment" ).
                thenReturn();
    }

    private Response getFakePost(){
        int postId = from(postResponse.getBody().asString()).get("id");

        return given().request().get("/api/post/"+postId).thenReturn();
    }
}
