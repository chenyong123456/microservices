package cn.knowimage.mapper;

import cn.knowimage.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    String findUserNameById(@Param("id") String id);
    String findIdByUserName(@Param("username") String username);
    List<User> findAllUserNameAndId();
    String checkUser(@Param("username") String username,@Param("password") String password);
}


