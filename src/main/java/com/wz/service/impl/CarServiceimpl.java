package com.wz.service.impl;

import com.wz.mapper.CarMapper;
import com.wz.pojo.Car;
import com.wz.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceimpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getCarList() {
        List<Car> carList= carMapper.findAllcar();
        return carList;
    }
}
