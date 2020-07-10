package com.reason.mapper;


import com.reason.config.sql.CurrentDataSource;
import com.reason.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT id, name, email, phone FROM users")
    @CurrentDataSource("db1")
    public List<UserInfo> selectUser();


    @Insert("<script> " +
            "INSERT INTO users (id, name, email, phone) " +
            "values " +
            "<foreach collection=\"userInfos\" index=\"index\" item=\"item\" separator=\",\"> " +
            "(#{item.id}, #{item.name}, #{item.email}, #{item.phone}) " +
            "</foreach> " +
            "ON DUPLICATE KEY UPDATE " +
            "name = VALUES(name), email = VALUES(email), phone = VALUES(phone)" +
            "</script>")
    @CurrentDataSource("db2")
    public int batchUpdateInfo(@Param("userInfos") List<UserInfo> userInfos);

}
