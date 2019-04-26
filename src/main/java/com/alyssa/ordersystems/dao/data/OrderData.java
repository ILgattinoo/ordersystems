package com.alyssa.ordersystems.dao.data;

import com.alyssa.ordersystems.constant.DataType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "orders")

public class OrderData extends BaseData{

    private long orderId;

    private int orderAmount;

    private long userId;

    @Enumerated(value = EnumType.STRING)
    private DataType.OrderType orderType;


}
