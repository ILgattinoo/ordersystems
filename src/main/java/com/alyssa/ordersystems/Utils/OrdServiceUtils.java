package com.alyssa.ordersystems.Utils;

import java.util.UUID;

import static com.alyssa.ordersystems.constant.Const.DEFAULT_RANDOM_RANGE_BYTES;

public class OrdServiceUtils {

    public static long generateId() {
        return getRandomNum() + (System.currentTimeMillis() << DEFAULT_RANDOM_RANGE_BYTES);
    }

    private static long getRandomNum() {
        long rangeMaxValue = 1 << DEFAULT_RANDOM_RANGE_BYTES;
        return UUID.randomUUID().hashCode() & (rangeMaxValue - 1) + rangeMaxValue;
    }

}
