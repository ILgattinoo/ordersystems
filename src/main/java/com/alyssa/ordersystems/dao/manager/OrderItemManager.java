package com.alyssa.ordersystems.dao.manager;

import com.alyssa.ordersystems.dao.data.OrderItemData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemManager extends JpaRepository<OrderItemData,Long> {
    @Query(nativeQuery = true,value = "select * from order_items where order_id = :orderId")
    List<OrderItemData> findByOrderId(@Param("orderId") long orderId);

}
