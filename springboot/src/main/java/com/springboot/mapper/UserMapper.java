package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 在mapper层可以写动态sql语句
@Mapper
public interface UserMapper { // mapper接口与数据库相连  BaseMapper
    @Select("SELECT * from user") // 查询用户表中所有数据的一个mapper
    List<User> findAll();

    @Insert("INSERT into user(username,passwords,age,sex,phone) VALUES (#{username},#{passwords},#{age},#{sex},#{phone})")
//    // 具体的值需要用#{vaule}封装
    int insert(User user); // 从外面传过来的

//  @Update("update user set username = #{username},passwords=#{passwords},age=#{age},sex=#{sex},phone=#{phone} where id = #{id}")
    int update(User user);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
// @Param

//    List<User> list();

    @Select("select * from user where username like #{username} limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize,String username);

    @Select("select count(id) from user ")  // 或者是  select count(*) from user 都是查询数据总条数
    Integer countTotal(String username);

    @Select("select * from user where adderss like #{address} limit #{pageNum},#{pageSize}")
    List<User> selectPageAddr(Integer pageNum, Integer pageSize, String address);

    @Select("select count(id) from user ")
    Integer countTotalAddr(String address);
}