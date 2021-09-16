package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "note-submit-button")
    private WebElement noteSubmitButton;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-submit-button")
    private WebElement credentialSubmitButton;

    @FindBy(id = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id = "delete-credential-button")
    private WebElement deleteCredentialButton;

    private WebDriver webDriver;

    private WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void clickNotesTab() {
        this.notesTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
    }

    public void clickAddNoteButton() {
        this.addNoteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
    }

    public void submitNoteForm(String noteTitle, String noteDescription) {
        this.noteTitle.clear();
        this.noteDescription.clear();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        this.noteSubmitButton.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("table-note-title")));
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
    }

    public String getFirstNoteTitle() {
        return webDriver.findElement(By.id("table-note-title")).getText();
    }

    public String getFirstNoteDescription() {
        return webDriver.findElement(By.id("table-note-description")).getText();
    }

    public void clickEditNoteButton() {
        this.editNoteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
    }

    public void clickDeleteNoteButton() {
        this.deleteNoteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
    }

    public void clickCredentialsTab () {
        this.credentialsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
    }

    public void clickAddCredentialButton() {
        this.addCredentialButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl));
    }

    public void submitCredentialForm(String credentialUrl, String credentialUsername, String credentialPassword) {
        this.credentialUrl.clear();
        this.credentialUsername.clear();
        this.credentialPassword.clear();

        this.credentialUrl.sendKeys(credentialUrl);
        this.credentialUsername.sendKeys(credentialUsername);
        this.credentialPassword.sendKeys(credentialPassword);

        this.credentialSubmitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
    }

    public void clickEditCredentialButton () {
        this.editCredentialButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrl));
    }

    public String getFirstCredentialUrl() {
        return webDriver.findElement(By.id("table-credential-url")).getText();
    }

    public String getFirstCredentialUsername() {
        return webDriver.findElement(By.id("table-credential-username")).getText();
    }

    public void clickDeleteCredentialButton() {
        this.deleteCredentialButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
    }
}
