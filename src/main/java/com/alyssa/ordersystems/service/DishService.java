package com.alyssa.ordersystems.service;

import com.alyssa.ordersystems.constant.DataType;
import com.alyssa.ordersystems.dao.data.DishData;
import com.alyssa.ordersystems.dao.manager.DishManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.alyssa.ordersystems.constant.DataType.DishType.HOTDISH;


@Slf4j
@Service
public class DishService {
    @Autowired
    private DishManager manager;

    @Transactional
    public boolean addDish(String dishName, int dishType, int dishPrice ,int dishStock) {
        if (manager.getByDishName(dishName).isPresent()) {
            return false;
        }
        create(dishName, DataType.DishType.getType(dishType).orElse(HOTDISH),dishPrice,dishStock);
        return true;
    }

    private void create(String dishName,DataType.DishType dishType, int dishPrice ,int dishStock) {
        manager.save(new DishData()
                .setDishName(dishName)
                .setDishType(dishType)
                .setDishPrice(dishPrice)
                .setDishStock(dishStock));
    }

    @Transactional
    public DishData updateDish(long id, String dishName, int dishType, int dishPrice ,int dishStock)
            throws Exception {
        Optional<DishData> data = manager.findById(id);
        if (!data.isPresent()) {
            throw new Exception("The dish does not exist. id=" + id);
        }
        if (!StringUtils.isBlank(dishName)) {
            data.get().setDishName(dishName);
        }

        DataType.DishType.getType(dishType).ifPresent(
                dishType1-> data.get().setDishType(dishType1));

        if (dishPrice >= 0) {
            data.get().setDishPrice(dishPrice);
        }
        if (dishStock >= 0) {
            data.get().setDishStock(dishStock);
        }
        return manager.save(data.get());
    }

    public void deleteDish(long id) {
        manager.findById(id).ifPresent((o) -> manager.delete(o));
    }

    public DishData getByDishName(String dishName) {
        return manager.getByDishName(dishName).orElse(null);
    }

    public List<DishData> listAllDish(int pageNumber, int pageSize) {
        return manager.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();
    }

}
