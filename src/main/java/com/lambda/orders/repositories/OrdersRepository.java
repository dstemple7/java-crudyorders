package com.lambda.orders.repositories;

import com.lambda.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long>
{

    List<Order> findAllByAdvanceamountGreaterThan(double advanceamount);

}
