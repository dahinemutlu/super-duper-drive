package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialTests extends SignupLoginTests{

    @Test
    //Write a Selenium test that logs in an existing user, creates a credential and verifies that the credential details are visible in the credential list.
    public void testCreateCredential () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickCredentialsTab();
        homePage.clickAddCredentialButton();
        homePage.submitCredentialForm("http://www.example.com", "dnemutlu", "XJaZWD2W");
    }

    // Write a Selenium test that logs in an existing user with existing credentials, clicks the edit credential button on an existing credential,
    // changes the credential data, saves the changes, and verifies that the changes appear in the credential list.
    @Test
    public void testEditCredential () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickCredentialsTab();
        homePage.clickAddCredentialButton();
        homePage.submitCredentialForm("http://www.example.com", "dnemutlu", "XJaZWD2W");
        homePage.clickEditCredentialButton();
        homePage.submitCredentialForm("http://www.example2.com", "dnemutlu2", "XJaZWD2W2");
        assertEquals("http://www.example2.com", homePage.getFirstCredentialUrl());
        assertEquals("dnemutlu2", homePage.getFirstCredentialUsername());
    }

    @Test
    // Write a Selenium test that logs in an existing user with existing credentials, clicks the delete credential button on an existing credential,
    // and verifies that the credential no longer appears in the credential list.
    public void testDeleteCredential () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickCredentialsTab();
        homePage.clickAddCredentialButton();
        homePage.submitCredentialForm("http://www.example.com", "dnemutlu", "XJaZWD2W");
        homePage.clickDeleteCredentialButton();

        try{
            homePage.getFirstCredentialUrl();
            fail("Credential still exists");
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }
}
