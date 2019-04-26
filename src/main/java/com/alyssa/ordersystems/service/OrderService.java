package com.alyssa.ordersystems.service;


import com.alyssa.ordersystems.Utils.OrdServiceUtils;
import com.alyssa.ordersystems.constant.DataType;
import com.alyssa.ordersystems.dao.data.OrderData;
import com.alyssa.ordersystems.dao.manager.OrderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.alyssa.ordersystems.constant.DataType.OrderType.UNPAID;


@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderManager manager;

    @Transactional
    public boolean addOrder(int orderAmount, long userId,int orderType) {

        create(orderAmount, userId,DataType.OrderType.getType(orderType).orElse(UNPAID));
        return true;
    }

    private void create(int orderAmount, long userId,DataType.OrderType orderType) {
        manager.save(new OrderData()
                .setOrderId(OrdServiceUtils.generateId())
                .setOrderAmount(orderAmount)
                .setUserId(userId)
                .setOrderType(orderType));
    }

    @Transactional
    public OrderData updateOrder(long id, int orderAmount,int orderType)
            throws Exception {
        Optional<OrderData> data = manager.findById(id);

        data.get().setOrderAmount(orderAmount);

        DataType.OrderType.getType(orderType).ifPresent(
                orderType1-> data.get().setOrderType(orderType1));

        return manager.save(data.get());
    }

    public void deleteOrder(long id) {
        manager.findById(id).ifPresent((o) -> manager.delete(o));
    }

    public OrderData getByOrderId(long orderId) {
        return manager.getByOrderId(orderId).orElse(null);
    }

    public List<OrderData> listAllOrder(int pageNumber, int pageSize) {
        return manager.findAll(PageRequest.of(pageNumber - 1, pageSize)).getContent();
    }

}
