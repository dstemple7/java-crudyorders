package com.lambda.orders.services;

import com.lambda.orders.models.Order;
import com.lambda.orders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrdersRepository orderrepos;

    @Override
    public Order findOrderById(long id)
    {
        return orderrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));
    }

    @Override
    public List<Order> findAdvanceAmount(double advanceamount)
    {
        List<Order> list = orderrepos.findAllByAdvanceamountGreaterThan(advanceamount);
        return list;
    }

    @Override
    public Order save(Order order){
        return orderrepos.save(order);
    }
}
