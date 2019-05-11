package com.alyssa.ordersystems.controller;


import com.alyssa.ordersystems.service.OrderService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    @PostMapping(value = "/add")
    public Map<String, Object> addOrder(
            @RequestParam(value = "orderAmount") int orderAmount,
            @RequestParam(value = "userId")long userId,
            @RequestParam(value = "orderType")int orderType){
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.addOrder(orderAmount, userId,orderType));
        return model;
    }

    @PostMapping(value = "/delete")
    public Map<String, Object> delete(@RequestParam(value = "id") long id) {

        service.deleteOrder(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

    @PostMapping(value = "/update")
    public Map<String, Object> update(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "orderAmount", required = false) int orderAmount,
            @RequestParam(value = "orderType", required = false, defaultValue = "0") int orderType) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        model.put("order", service.updateOrder(id, orderAmount, orderType));
        return model;
    }

    @PostMapping(value = "/getByOrderId")
    public Map<String, Object> getByOrderId(@RequestParam(value = "orderId") long orderId) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("order", service.getByOrderId(orderId));
        return model;
    }

    @PostMapping(value = "/listAll")
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("dataList", service.listAllOrder(pageNumber, pageSize));
        return model;
    }
}
