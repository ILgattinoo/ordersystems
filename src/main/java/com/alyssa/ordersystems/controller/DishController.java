package com.alyssa.ordersystems.controller;

import com.alyssa.ordersystems.service.DishService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService service;
//String dishName, int dishType, int dishPrice ,int dishStock
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Map<String, Object> addDish(
            @RequestParam(value = "dishName") String dishName,
            @RequestParam(value = "dishType") int dishType,
            @RequestParam(value = "dishPrice")int dishPrice,
            @RequestParam(value = "dishStock")int dishStock ){
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.addDish(dishName, dishType,dishPrice,dishStock));
        return model;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Map<String, Object> delete(@RequestParam(value = "id") long id) {

        service.deleteDish(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public boolean update(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "dishName", required = false, defaultValue = "") String DishName,
            @RequestParam(value = "dishPrice", required = false, defaultValue = "0") int dishPrice,
            @RequestParam(value = "dishStock", required = false, defaultValue = "0") int dishStock) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        service.updateDish(id, DishName, dishPrice, dishStock);
        return true;

    }

    @RequestMapping(value = "/getByDishName",method = RequestMethod.GET)
    public Map<String, Object> getByDishName(@RequestParam(value = "dishName") String dishName) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("dish", service.getByDishName(dishName));
        return model;
    }

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("dishList", service.listAllDish(pageNumber, pageSize));
        return model;
    }
}
