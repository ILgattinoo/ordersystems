package com.alyssa.ordersystems.controller;


import com.alyssa.ordersystems.service.OrderService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Map<String, Object> addOrder(
            @RequestParam(value = "orderAmount") int orderAmount,
            @RequestParam(value = "userId")String userId,
            @RequestParam(value = "orderType")int orderType){
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.addOrder(orderAmount, userId,orderType));
        return model;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Map<String, Object> delete(@RequestParam(value = "id") long id) {

        service.deleteOrder(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Map<String, Object> update(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "orderAmount", required = false) int orderAmount,
            @RequestParam(value = "orderType", required = false, defaultValue = "0") int orderType) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        model.put("order", service.updateOrder(id, orderAmount, orderType));
        return model;
    }

    @RequestMapping(value = "/getByOrderId",method = RequestMethod.GET)
    public Map<String, Object> getByOrderId(@RequestParam(value = "orderId") long orderId) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("order", service.getByOrderId(orderId));
        return model;
    }

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("orderList", service.listAllOrder(pageNumber, pageSize));
        return model;
    }
}
