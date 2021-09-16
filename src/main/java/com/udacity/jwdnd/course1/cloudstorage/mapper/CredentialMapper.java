package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE USERID = #{userId}")
    List<Credential> getUserCredentials(int userId);

    @Insert("INSERT INTO CREDENTIALS (URL, USERNAME, KEY, PASSWORD, USERID) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET URL = #{url}, USERNAME = #{username}, KEY = #{key}, PASSWORD = #{password} WHERE CREDENTIALID = #{credentialId}")
    public void updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE CREDENTIALID = #{credentialId}")
    void deleteCredential(int credentialId);
}
