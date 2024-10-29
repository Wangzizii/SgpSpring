package com.wz.controller;

import com.wz.pojo.Result;
import com.wz.pojo.Todolist;
import com.wz.pojo.User;
import com.wz.service.TodolistService;
import com.wz.service.UserService;
import com.wz.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin
public class TodolistController {

    @Autowired
    TodolistService todolistService;
    @Autowired
    UserService userService;

    @PostMapping("/listall")
    public Result listAll(String username) {
        User user = userService.findbyUsername(username);
        try {
            List<Todolist> list =todolistService.listAll(user.getId());
            return Result.success("Your TodoList",list);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.error("");
        }

    }
    @PostMapping("/add")
    public Result addTodo(String title, String content, HttpServletRequest request){
        String username=JWTUtil.verifyToken(request.getHeader("Authorization")).get("username").toString();
        System.out.println(username);
        User user = userService.findbyUsername(username);
        try {
            todolistService.addTodo(user.getId(),title,content);
            return Result.success("Add Success");
        }
        catch (Exception e) {
            return Result.error("");
        }

    }
    @PostMapping("/update")
    public Result updateTodo(@RequestBody List<Todolist> updatelist){
        try {
            todolistService.updateTodo(updatelist);
            return  Result.success("Update Success");
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed");
        }
    }


}
