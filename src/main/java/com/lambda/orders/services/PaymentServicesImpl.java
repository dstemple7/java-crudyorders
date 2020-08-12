package com.lambda.orders.services;

import com.lambda.orders.models.Payment;
import com.lambda.orders.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "paymentServices")
public class PaymentServicesImpl implements PaymentsServices
{
    @Autowired
    PaymentsRepository paymentrepos;

    @Override
    public Payment save(Payment payment){
        return paymentrepos.save(payment);
    }
}
