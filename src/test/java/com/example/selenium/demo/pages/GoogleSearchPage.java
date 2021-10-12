package com.example.selenium.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {

    private final JavascriptExecutor js;


    private final WebDriver driver;

    //Google Search Page - WebElements
    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")
    private WebElement searchInput;

    @FindBy(name = "btnK")
    private WebElement submitButton;

    @FindBy(id = "L2AGLb")
    private WebElement cookieButton;


    public GoogleSearchPage( WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String title() {
        String pageTitle = driver.getTitle();
        System.out.println("Title of Homepage: " + pageTitle);
        return pageTitle;
    }

    public void search(String searchTerm) {
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOf(searchInput));

        js.executeScript("arguments[0].value='" + searchInput + "';", searchTerm);
        //searchInput.sendKeys(searchTerm);
    }

    public void submit() {
        new WebDriverWait(driver, 4).until(ExpectedConditions.elementToBeClickable(searchInput));
        //submitButton.submit();
        js.executeScript("arguments[0].click();",submitButton);
    }

    public int showAllResults() {
        List<WebElement> allSearchResults = driver.findElements(By.xpath("//div[@class = 'g']")).stream()
                .filter(r -> !r.findElement(By.tagName("h3")).getText().isEmpty())
                .collect(Collectors.toList());

      //  new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfAllElements(
              //  allSearchResults));


        for (WebElement eachResult : allSearchResults) {
            WebElement title = eachResult.findElement(By.tagName("h3"));
            WebElement link = eachResult.findElement(By.cssSelector(".yuRUbf>a"));

            System.out.println("Title : " + title.getText() + ", Link : " + link.getAttribute("href"));
        }

        return allSearchResults.size();
    }

    public void clickCookieButton(){
        new WebDriverWait(driver, 4).until(ExpectedConditions.elementToBeClickable(cookieButton));
        cookieButton.click();
    }
}
