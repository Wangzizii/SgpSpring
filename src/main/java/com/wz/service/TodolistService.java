package com.wz.service;

import com.wz.pojo.Todolist;

import java.util.List;

public interface TodolistService {
     List<Todolist> listAll(int user_id);

     void addTodo(int user_id,String title,String content);

}
