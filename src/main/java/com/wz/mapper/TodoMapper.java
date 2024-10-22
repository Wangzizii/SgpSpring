package com.wz.mapper;

import com.wz.pojo.Todolist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TodoMapper {
    @Select("SELECT  * from todolist where user_id=#{user_id} ORDER BY create_time")
    List<Todolist> Listall(int user_id);

    @Insert("INSERT INTO todolist (user_id,title,content,status,create_time) values (#{user_id},#{title},#{content},0,now())")
    void addTodo(int user_id,String title,String content);


}
