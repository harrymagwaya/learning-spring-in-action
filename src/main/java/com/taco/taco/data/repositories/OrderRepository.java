package com.taco.taco.data.repositories;

import com.taco.taco.data.Order;

public interface OrderRepository {
    Order save(Order order);
}
