package com.wz.service.impl;

import com.wz.mapper.TodoMapper;
import com.wz.pojo.Todolist;
import com.wz.service.TodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodolistServiceimpl implements TodolistService {
    @Autowired
    private TodoMapper todoMapper;
    @Override
    public List<Todolist> listAll(int user_id) {
        List<Todolist> todolist=todoMapper.Listall(user_id);

        return todolist;
    }

    @Override
    public void addTodo(int user_id, String title, String content) {

        todoMapper.addTodo(user_id,title,content);
        return;

    }
}
