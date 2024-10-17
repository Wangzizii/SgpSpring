package com.wz.pojo;

import lombok.Data;

@Data
public class Car {
    private Integer id;
    private String carName;
    private int milesLeft;
    private int charged;
    private String picture;
}
