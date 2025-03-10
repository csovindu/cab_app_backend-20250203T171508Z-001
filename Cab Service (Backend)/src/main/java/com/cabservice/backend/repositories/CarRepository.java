package com.cabservice.backend.repositories;

import com.cabservice.backend.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findByStatus(String status);
}
