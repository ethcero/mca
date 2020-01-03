package es.codeurjc.daw.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author fran
 */
public class BlogPage extends Page {

    public BlogPage(WebDriver driver, int port) {
        super(driver, port);
    }

    public BlogPage(Page page) {
        super(page);
    }

    public BlogPage get(){
        this.get("/");
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
        return this;
    }

    public NewPostPage newPost() {
        findElementWithText("Nuevo post").click();
        return new NewPostPage(this);
    }


}
