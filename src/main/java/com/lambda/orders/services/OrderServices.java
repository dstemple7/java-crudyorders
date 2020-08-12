package com.lambda.orders.services;

import com.lambda.orders.models.Order;

import java.util.List;

public interface OrderServices
{
    Order findOrderById(long id);

    Order save(Order order);

    void delete(long id);

    Order update(Order order, long id);

    List<Order> findAdvanceAmount(double advanceamount);
}
