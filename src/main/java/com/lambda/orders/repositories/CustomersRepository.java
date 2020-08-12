package com.lambda.orders.repositories;

import com.lambda.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long>
{
    Customer findByCustname(String name);

    List<Customer> findByCustnameContainingIgnoringCase(String likename);

}
