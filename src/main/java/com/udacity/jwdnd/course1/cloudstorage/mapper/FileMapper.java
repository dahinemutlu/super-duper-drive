package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE USERID = #{userId}")
    List<File> getUserFiles(int userId);

    @Insert("INSERT INTO FILES (FILENAME, CONTENTTYPE, FILESIZE, USERID, FILEDATA) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE FILEID = #{fileId}")
    void deleteFile(int fileId);

    @Select("SELECT * FROM FILES WHERE FILEID = #{fileId}")
    File getFileById(int fileId);
}
