package com.lambda.orders.services;

import com.lambda.orders.models.Customer;
import com.lambda.orders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImpl implements CustomerServices
{
    @Autowired
    CustomersRepository custrepos;

    @Override
    public Customer findCustomerById(long id)
    {
        return custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findAllCustomers()
    {
        List<Customer> list = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Customer> findAllCustomersByNameLike(String custname)
    {
        return custrepos.findByCustnameContainingIgnoringCase(custname);
    }

    @Override
    public Customer save(Customer customer){
        return custrepos.save(customer);
    }
}
