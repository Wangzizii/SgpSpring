package com.wz.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Todolist {
    private int id;
    private  int user_id;
    private String title;
    private String content;
    private boolean status;
    private Date create_time;


}
