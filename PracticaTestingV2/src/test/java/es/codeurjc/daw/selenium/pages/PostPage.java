package es.codeurjc.daw.selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author fran
 */
public class PostPage extends Page {

    public PostPage(WebDriver driver, int port) {
        super(driver, port);
    }

    public PostPage(Page page) {
        super(page);
    }

    public PostPage get(Long id){
        this.get("/post/"+id);
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
        return this;
    }

    public PostPage assertPostContent(String title, String content)  {
        Assert.assertTrue(isElementPresent(title));
        Assert.assertTrue(isElementPresent(content));
        return this;
    }

    public PostPage assertPostComment() {
        Assert.assertTrue(isElementWithClassNamePresent("comment"));
        return this;
    }

    public PostPage newComment(String author, String message) {
        driver.findElement(By.id("author")).sendKeys(author);
        driver.findElement(By.id("message")).sendKeys(message);
        return this;
    }
    public PostPage submitComment() {
        driver.findElement(By.id("newCommentForm")).submit();
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
        return this;
    }

    public PostPage deleteComment() {
        driver.findElement(By.id("deleteCommentForm")).submit();
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
        return this;
    }

    public PostPage assertCommentDeleted() {
        Assert.assertFalse(isElementWithClassNamePresent("comment"));
        return this;
    }
}
