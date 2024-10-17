package com.wz.controller;

import com.wz.pojo.Car;
import com.wz.pojo.Result;
import com.wz.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/gettList")
    public Result getlist(){
        List<Car> carList=carService.getCarList();
        System.out.println(carList);
        return Result.success("车辆列表",carList);
    }


}
