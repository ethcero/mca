package es.codeurjc.daw.restassured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.contains;
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

/**
 * @author fran
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogRestControllerTest {

    @LocalServerPort
    int port;

    Post fakePost;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        fakePost = createFakePost();
    }

    @Test
    public void givenPostWhenCreateNewPostThenReturnTheCreatedPost(){
        getFakePost()
            .then()
            .statusCode(200)
            .body("id", equalTo((int)fakePost.getId()))
            .body("title", equalTo(fakePost.getTitle()));

    }

    @Test
    public void givenPostWhenCreateNewCommentThenReturnTheCreatedComment(){

        Comment comment = createFakeComment();

        getFakePost()
                .then()
                .body("comments", not(empty()))
                .body("comments.id", contains((int)comment.getId()));

    }

    @Test
    public void givenPostWithCommentsWhenDeleteCommentThenCommentDeleted(){
        Comment comment = createFakeComment();

        given()
                .request()
                .delete("/api/post/" + fakePost.getId() + "/comment/" + comment.getId())
                .then().statusCode(204);

        getFakePost()
                .then()
                .body("comments",empty());
    }


    private Post createFakePost(){
        Post expectedPost = new Post("testTitle", "testContent");

        Response response =  given()
                .body(expectedPost)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/post")
                .thenReturn();

        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("title", equalTo(expectedPost.getTitle()));

        return response.as(Post.class);
    }

    private Comment createFakeComment() {

        Comment expectedComment = new Comment("authorTest", "messagetest");

        Response response = given()
                .body(expectedComment)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/post/" + fakePost.getId() + "/comment" )
                .thenReturn();

        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("author", equalTo(expectedComment.getAuthor()))
                .body("message", equalTo(expectedComment.getMessage()));

        return response.as(Comment.class);
    }

    private Response getFakePost(){
        return given()
                .request()
                .get("/api/post/"+fakePost.getId())
                .thenReturn();
    }
}
