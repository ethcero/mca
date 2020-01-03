package es.codeurjc.daw.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import es.codeurjc.daw.Application;
import es.codeurjc.daw.selenium.pages.BlogPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author fran
 */

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogWebControllerTest {
    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void giveEmptyBlogWhenCreateNewPostThenNewPostCreated() {
        BlogPage blog = new BlogPage(driver, port);

        String title = "test Title";
        String content = "test content";

        blog.get()
                .newPost()
                .fillNewPost(title,content)
                .submit()
                .assertPostContent(title, content);
    }

    @Test
    public void givePostWhenCreateNewCommentThenNewCommentCreated() {
        BlogPage blog = new BlogPage(driver, port);

        String title = "test Title";
        String content = "test content";

        String author = "test author";
        String message = "test message";

        blog.get()
                .newPost()
                .fillNewPost(title,content)
                .submit()
                .assertPostContent(title, content)
                .newComment(author, message)
                .submitComment()
                .assertPostComment();
    }

    @Test
    public void givePostWithCommentWhenDeleteCommentThenCommentDeleted() {
        BlogPage blog = new BlogPage(driver, port);

        String title = "test Title";
        String content = "test content";

        String author = "test author";
        String message = "test message";

        blog.get()
                .newPost()
                .fillNewPost(title,content)
                .submit()
                .assertPostContent(title, content)
                .newComment(author, message)
                .submitComment()
                .assertPostComment()
                .deleteComment()
                .assertCommentDeleted();
    }
}
