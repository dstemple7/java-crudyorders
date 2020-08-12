package com.lambda.orders.services;

import com.lambda.orders.models.Order;

import java.util.List;

public interface OrderServices
{
    Order findOrderById(long id);

    Order save(Order order);

    List<Order> findAdvanceAmount(double advanceamount);
}
