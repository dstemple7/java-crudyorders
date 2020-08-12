package com.lambda.orders.services;

import com.lambda.orders.models.Order;
import com.lambda.orders.models.Payment;
import com.lambda.orders.repositories.CustomersRepository;
import com.lambda.orders.repositories.OrdersRepository;
import com.lambda.orders.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderServices")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    OrdersRepository orderrepos;

    @Autowired
    PaymentsRepository paymentrepos;

    @Autowired
    CustomersRepository custrepos;

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

    @Transactional
    @Override
    public Order save(Order order)
    {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0)
        {
            orderrepos.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        // payments, many to many or one to many???
        newOrder.getPayments()
            .clear();
        for (Payment p : order.getPayments())
        {
            Payment newPay = paymentrepos.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

            newOrder.getPayments()
                .add(newPay);
        }

        return orderrepos.save(newOrder);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (orderrepos.findById(id)
            .isPresent())
        {
            orderrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order " + id + " Not Found");
        }

    }

    @Transactional
    @Override
    public Order update(Order order,
                        long id)
    {
        Order currentOrder = orderrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));

        if (order.getOrdamount() > 0)
        {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if (order.getAdvanceamount() > 0)
        {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if (order.getOrderdescription() != null)
        {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

        if (order.getCustomer() != null)
        {
            currentOrder.setCustomer(order.getCustomer());
        }
        // payments, many to many or one to many???
        if (order.getPayments()
            .size() > 0)
        {
            currentOrder.getPayments()
                .clear();
            for (Payment p : order.getPayments())
            {
                Payment newPay = paymentrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));

                currentOrder.getPayments()
                    .add(newPay);
            }
        }

            return orderrepos.save(currentOrder);

    }
}