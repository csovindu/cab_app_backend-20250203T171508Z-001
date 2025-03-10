package com.cabservice.backend.controllers;

import com.cabservice.backend.models.Car;
import com.cabservice.backend.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Add a new car
    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    // Get all cars
    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    // Get a car by ID
    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable String id) {
        return carService.getCarById(id);
    }

    // Get cars by status
    @GetMapping("/status/{status}")
    public List<Car> getCarsByStatus(@PathVariable String status) {
        return carService.getCarsByStatus(status);
    }

    // Update car details
    @PutMapping("/update/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    // Delete a car by ID
    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable String id) {
        return carService.deleteCar(id);
    }
}
