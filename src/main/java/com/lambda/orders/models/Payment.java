package com.lambda.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long paymentid;

    @Column(nullable = false)
    private String type;

    public Payment()
    {
    }

    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties(value = "payments")
    private List<Order> orders = new ArrayList<>();

    public Payment(String type)
    {
        this.type = type;
    }

    public long getPaymentid()
    {
        return paymentid;
    }

    public void setPaymentid(long paymentid)
    {
        this.paymentid = paymentid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Payment{" +
            "paymentid=" + paymentid +
            ", type='" + type + '\'' +
            '}';
    }
}