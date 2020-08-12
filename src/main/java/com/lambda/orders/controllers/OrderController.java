package com.lambda.orders.controllers;

import com.lambda.orders.models.Order;
import com.lambda.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{ordnum}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long ordnum)
    {
        Order myOrder = orderServices.findOrderById(ordnum);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }

    // http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = "application/json")
    public ResponseEntity<?> getAdvanceAmount()
    {
        List<Order> myList = orderServices.findAdvanceAmount(0);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

}
