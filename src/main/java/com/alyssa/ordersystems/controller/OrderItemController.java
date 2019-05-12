package com.alyssa.ordersystems.controller;

import com.alyssa.ordersystems.service.OrderItemService;
import com.alyssa.ordersystems.service.OrderService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.alyssa.ordersystems.constant.Const.StatusField.RESULT;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService service;
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Map<String, Object> addOrderItem(
            @RequestParam(value = "orderId")  long orderId,
            @RequestParam(value = "dishName")String dishName,
            @RequestParam(value = "dishPrice")int dishPrice){
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, service.addOrderItem(orderId, dishName,dishPrice));
        return model;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Map<String, Object> delete(@RequestParam(value = "id") long id) {

        service.deleteOrderItem(id);
        Map<String, Object> model = Maps.newHashMap();
        model.put(RESULT, Boolean.TRUE);
        return model;
    }

//    @PostMapping(value = "/update")
//    public Map<String, Object> update(
//            @RequestParam(value = "id") long id,
//            @RequestParam(value = "orderAmount", required = false) int orderAmount,
//            @RequestParam(value = "orderType", required = false, defaultValue = "0") int orderType) throws Exception {
//
//        Map<String, Object> model = Maps.newHashMap();
//        model.put("order", service.updateOrder(id, orderAmount, orderType));
//        return model;
//    }

    @RequestMapping(value = "/getByOrderId",method = RequestMethod.GET)
    public Map<String, Object> getByOrderId(@RequestParam(value = "orderId") long orderId) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("OrderItemList", service.getByOrderId(orderId));
        return model;
    }

    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public Map<String, Object> listAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("dataList", service.listAllOrderItem(pageNumber, pageSize));
        return model;
    }
}
