package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public List<Note> getUserNotes(int userId) {
        return noteMapper.getUserNotes(userId);
    }

    public void addNote (Note note) {
        noteMapper.addNote(note);
    }

    public void updateNote (Note note) {
        noteMapper.updateNote(note);
    }

    public void deleteNote (Integer noteId) {
        noteMapper.deleteNote(noteId);
    }
}
