package com.kumaral.carservice;

import com.kumaral.carservice.model.Car;
import com.kumaral.carservice.persistence.CarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Value("${car.service.config.msg:Config Server is not working.}")
    private String message;

    @Resource private CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/cars/{name}")
    public Car getCarByName(@PathVariable final String name) {
        return getCars().stream().filter(c -> c.getName().equals(name)).findFirst().get();
    }

    @GetMapping("/msg")
    public String getCarMessage(@RequestHeader("model") final String model) {
        return message + model ;
    }
}
