package com.lambda.orders.services;

import com.lambda.orders.models.Customer;
import com.lambda.orders.models.Order;
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

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            custrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("CUstomer " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());

        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        // one to many, orders
        newCustomer.getOrders().clear();
        for(Order o : customer.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(newCustomer);
            newOrder.setOrderdescription(o.getOrderdescription());
            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer,
                                long id)
    {
        Customer currentCustomer = custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        if (customer.getCustname() != null){
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null){
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null){
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null){
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null){
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getOpeningamt() > 0){
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.getReceiveamt() > 0){
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.getPaymentamt() > 0){
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.getOutstandingamt() > 0){
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null){
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null){
            currentCustomer.setAgent(customer.getAgent());
        }

        // one to many, orders
        if (customer.getOrders().size() > 0)
        {
            currentCustomer.getOrders()
                .clear();

            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setCustomer(currentCustomer);
                newOrder.setOrderdescription(o.getOrderdescription());

                currentCustomer.getOrders()
                    .add(newOrder);
            }
        }

        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }

    }
}
