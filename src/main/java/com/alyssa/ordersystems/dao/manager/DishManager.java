package com.alyssa.ordersystems.dao.manager;

import com.alyssa.ordersystems.dao.data.DishData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishManager extends JpaRepository<DishData,Long> {
    Optional<DishData> getByDishName(String dishName);
}
