package es.codeurjc.daw.mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.codeurjc.daw.Comment;
import es.codeurjc.daw.Post;

/**
 * @author fran
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BlogRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    Post fakePost;

    @BeforeEach
    public void beforeEach() throws Exception {
        fakePost = createFakePost();
    }


    @Test
    public void givenPostWhenCreateNewPostThenReturnTheCreatedPost() throws Exception{

        getFakePost()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo((int)fakePost.getId())));

    }

    @Test
    public void givenPostWhenCreateNewCommentThenReturnTheCreatedComment() throws Exception{
        Comment comment = createFakeComment();

        getFakePost()
                .andExpect(jsonPath("$.comments.[0].id", equalTo((int)comment.getId())))
                .andExpect(jsonPath("$.comments.[0].author", equalTo(comment.getAuthor())));


    }

    @Test
    public void givenPostWithCommentsWhenDeleteCommentThenCommentDeleted() throws Exception{

        Comment comment = createFakeComment();

        mvc.perform(delete("/api/post/"+fakePost.getId()+"/comment/"+comment.getId()))
                .andExpect(status().isNoContent());

        getFakePost()
                .andExpect(jsonPath("$.comments", empty()));



    }

    private Post createFakePost() throws Exception{
        Post expectedPost = new Post("testTitle", "testContent");

        MvcResult result =  mvc.perform(post("/api/post")
                .content(objectMapper.writeValueAsString(expectedPost))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.title", equalTo(expectedPost.getTitle())))
                .andReturn();

        return this.objectMapper.readValue(result.getResponse().getContentAsString(), Post.class);

    }

    private Comment createFakeComment() throws Exception{
        Comment expectedComment = new Comment("authorTest", "messagetest");

        MvcResult result = mvc.perform(post("/api/post/" + fakePost.getId() + "/comment")
                .content(objectMapper.writeValueAsString(expectedComment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(empty())))
                .andExpect(jsonPath("$.author", equalTo(expectedComment.getAuthor())))
                .andExpect(jsonPath("$.message", equalTo(expectedComment.getMessage())))
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), Comment.class);
    }

    private ResultActions getFakePost() throws Exception{
        return mvc.perform(get("/api/post/" + fakePost.getId())
                .contentType(MediaType.APPLICATION_JSON));
    }


}
