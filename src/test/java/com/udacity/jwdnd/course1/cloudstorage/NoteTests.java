package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTests extends SignupLoginTests{

    @Test
    // Write a Selenium test that logs in an existing user, creates a note and verifies that the note details are visible in the note list.
    public void testCreateNote () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickNotesTab();
        homePage.clickAddNoteButton();
        homePage.submitNoteForm("Note title", "Note description");
        assertEquals("Note title", homePage.getFirstNoteTitle());
        assertEquals("Note description", homePage.getFirstNoteDescription());
    }

    @Test
    // Write a Selenium test that logs in an existing user with existing notes, clicks the edit note button on an existing note,
    // changes the note data, saves the changes, and verifies that the changes appear in the note list.
    public void testEditNote () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickNotesTab();
        homePage.clickAddNoteButton();
        homePage.submitNoteForm("Note title", "Note description");
        assertEquals("Note title", homePage.getFirstNoteTitle());
        assertEquals("Note description", homePage.getFirstNoteDescription());
        homePage.clickEditNoteButton();
        homePage.submitNoteForm("Updated note title", "Updated note description");
        assertEquals("Updated note title", homePage.getFirstNoteTitle());
        assertEquals("Updated note description", homePage.getFirstNoteDescription());
    }

    // Write a Selenium test that logs in an existing user with existing notes, clicks the delete note button on an existing note,
    // and verifies that the note no longer appears in the note list.
    @Test
    public void testDeleteNote () throws InterruptedException {
        HomePage homePage = signupAndLogin();
        assertEquals("Home - SuperDuperDrive", driver.getTitle());
        homePage.clickNotesTab();
        homePage.clickAddNoteButton();
        homePage.submitNoteForm("Note title", "Note description");
        assertEquals("Note title", homePage.getFirstNoteTitle());
        assertEquals("Note description", homePage.getFirstNoteDescription());

        homePage.clickDeleteNoteButton();

        try{
            homePage.getFirstNoteTitle();
            fail("Note still exists");
        }catch (NoSuchElementException e){
            assertTrue(true);
        }
    }
}
