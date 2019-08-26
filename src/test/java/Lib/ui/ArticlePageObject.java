package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "org.wikipedia:id/page_external_link";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElement(By.id(TITLE), "Cannot find article time for page ", 15);
    }

    public String getTitleElement() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void switeToFooter() {
        this.swipeUpToFindElement(
                By.id(FOOTER_ELEMENT),
                "Cant find article by 20 swipes",
                20
        );

    }
}
