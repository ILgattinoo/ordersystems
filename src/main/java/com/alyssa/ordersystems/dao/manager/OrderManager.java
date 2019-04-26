package com.alyssa.ordersystems.dao.manager;

import com.alyssa.ordersystems.dao.data.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderManager extends JpaRepository<OrderData,Long> {
    Optional<OrderData> getByOrderId(long orderId);

}
