package com.alyssa.ordersystems.constant;

import lombok.AllArgsConstructor;

import java.util.Optional;

public class DataType {
    @AllArgsConstructor
    public enum GenderType {
        UNKNOWN(1),

        MAN(2),

        WOMAN(3),;
        public final int code;

        public static Optional<GenderType> getType(int code) {
            GenderType type = null;
            for (GenderType t : GenderType.values()) {
                if (t.code == code) {
                    type = t;
                }
            }
            return Optional.ofNullable(type);
        }
    }

    @AllArgsConstructor
    public enum UserType {
        ADMINISTRATOR(1),

        CLERK(2),

        CUSTOMER(3),;

        public final int code;

        public static Optional<UserType> getType(int code) {
            UserType type = null;
            for (UserType t : UserType.values()) {
                if (t.code == code) {
                    type = t;
                }
            }
            if (type == ADMINISTRATOR) {
                type = ADMINISTRATOR;
            }
            return Optional.ofNullable(type);
        }

    }

    @AllArgsConstructor
    public enum DishType {
        HOTDISH(1),

        COLDDISH(2),

        DRINK(3),

        SOUP(4),

        MEAL(5),;
        public final int code;

        public static Optional<DishType> getType(int code) {
            DishType type = null;
            for (DishType t : DishType.values()) {
                if (t.code == code) {
                    type = t;
                }
            }
            return Optional.ofNullable(type);
        }
    }
    @AllArgsConstructor
    public enum OrderType {
        UNPAID(0),

        PAID(1),

        INVALID(-1),;

        public final int code;

        public static Optional<OrderType> getType(int code) {
            OrderType type = null;
            for (OrderType t : OrderType.values()) {
                if (t.code == code) {
                    type = t;
                }
            }
            return Optional.ofNullable(type);
        }
    }
}
