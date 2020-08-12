package com.lambda.orders.services;

import com.lambda.orders.models.Customer;

import java.util.List;

public interface CustomerServices
{

    List<Customer> findAllCustomers();

    Customer findCustomerById(long id);

    List<Customer> findAllCustomersByNameLike(String custname);

    Customer save(Customer customer);
}
