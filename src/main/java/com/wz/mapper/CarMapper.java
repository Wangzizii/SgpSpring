package com.wz.mapper;

import com.wz.pojo.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CarMapper {
    @Select("SELECT  * from cars")
    List<Car> findAllcar();
}
