package com.alyssa.ordersystems.controller;

import com.alyssa.ordersystems.service.DishService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/dish")
public class DishConcroller {

    @Autowired
    private DishService service;
//String dishName, int dishType, int dishPrice ,int dishStock
    @PostMapping(value = "/add")
    public Map<String, Object> addDish(
            @RequestParam(value = "dishName") String dishName,
            @RequestParam(value = "dishType") int dishType,
            @RequestParam(value = "dishPrice")int dishPrice,
            @RequestParam(value = "dishStock")int dishStock ){
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.addDish(dishName, dishType,dishPrice,dishStock));
        return model;
    }

    @PostMapping(value = "/delete")
    public Map<String, Object> delete(@RequestParam(value = "id") long id) {

        service.deleteDish(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

    @PostMapping(value = "/update")
    public Map<String, Object> update(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "dishName", required = false, defaultValue = "") String DishName,
            @RequestParam(value = "dishType", required = false, defaultValue = "1") int dishType,
            @RequestParam(value = "dishPrice", required = false, defaultValue = "0") int dishPrice,
            @RequestParam(value = "dishStock", required = false, defaultValue = "0") int dishStock) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        model.put("user", service.updateDish(id, DishName, dishType,
                dishPrice, dishStock));
        return model;
    }

    @PostMapping(value = "/getByDishName")
    public Map<String, Object> getByDishName(@RequestParam(value = "dishName") String dishName) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("user", service.getByDishName(dishName));
        return model;
    }

    @PostMapping(value = "/listAll")
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("dataList", service.listAllDish(pageNumber, pageSize));
        return model;
    }
}
