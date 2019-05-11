package com.alyssa.ordersystems.service;


import com.alyssa.ordersystems.dao.data.OrderItemData;
import com.alyssa.ordersystems.dao.manager.OrderItemManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderItemService {
    @Autowired
    private OrderItemManager manager;

    @Transactional
    public boolean addOrderItem(long orderId, String dishName,int dishPrice) {

        create(orderId, dishName,dishPrice);
        return true;
    }

    private void create(long orderId, String dishName,int dishPrice) {
        manager.save(new OrderItemData()
                .setOrderId(orderId)
                .setDishName(dishName)
                .setDishPrice(dishPrice));
    }

//    @Transactional
//    public OrderData updateOrder(long id, int orderAmount,int orderType)
//            throws Exception {
//        Optional<OrderData> data = manager.findById(id);
//
//        data.get().setOrderAmount(orderAmount);
//
//        DataType.OrderType.getType(orderType).ifPresent(
//                orderType1-> data.get().setOrderType(orderType1));
//
//        return manager.save(data.get());
//    }

    public void deleteOrderItem(long id) {
        manager.findById(id).ifPresent((o) -> manager.delete(o));
    }

    public List<OrderItemData> getByOrderId(long orderId) {
        return manager.findByOrderId(orderId);
    }

    public List<OrderItemData> listAllOrderItem(int pageNumber, int pageSize) {
        return manager.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();

    }

}