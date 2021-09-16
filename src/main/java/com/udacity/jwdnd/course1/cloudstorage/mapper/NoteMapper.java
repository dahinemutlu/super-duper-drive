package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE USERID = #{userId}")
    List<Note> getUserNotes(int userId);

    @Insert("INSERT INTO NOTES (NOTETITLE, NOTEDESCRIPTION, USERID) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Update("UPDATE NOTES SET NOTETITLE = #{noteTitle}, NOTEDESCRIPTION = #{noteDescription} WHERE NOTEID = #{noteId}")
    public void updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE NOTEID = #{noteId}")
    void deleteNote(int noteId);
}
