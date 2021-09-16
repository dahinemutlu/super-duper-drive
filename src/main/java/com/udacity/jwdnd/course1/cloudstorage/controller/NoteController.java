package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/write")
    public String writeNote(Authentication auth, Note note, RedirectAttributes ra){
        if (note.getNoteId() == null) {
            note.setUserId(userService.getUserId(auth.getName()));
            this.noteService.addNote(note);
            ra.addFlashAttribute("successMessage", "A new note has been added successfully");
        } else {
            ra.addFlashAttribute("successMessage", "The note has been edited successfully");
            this.noteService.updateNote(note);
        }

        return "redirect:/home#nav-notes";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, RedirectAttributes ra){
        noteService.deleteNote(noteId);
        ra.addFlashAttribute("successMessage", "The note has been deleted successfully");
        return "redirect:/home#nav-notes";
    }
}
