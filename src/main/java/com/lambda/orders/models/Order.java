package com.lambda.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordamount;

    @ManyToMany
    @JoinTable(name = "orderspayments",
        joinColumns = @JoinColumn(name = "ordnum"),
        inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties(value = "orders")
    private List<Payment> payments = new ArrayList<>();

    private double advanceamount;

    //    CUSTCODE Long foreign key (one customer to many orders) not null
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties(value = "orders")
    private Customer customer;

    private String orderdescription;

    public Order()
    {
    }

    public Order(
        double ordamount,
        double advanceamount,
        Customer customer,
        String orderdescription)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customer = customer;
        this.orderdescription = orderdescription;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getOrderdescription()
    {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription)
    {
        this.orderdescription = orderdescription;
    }

    public void addPayments(Payment p1){
        this.payments.add(p1);
    }

    public List<Payment> getPayments()
    {
        return payments;
    }

    public void setPayments(List<Payment> payments)
    {
        this.payments = payments;
    }

    @Override
    public String toString()
    {
        return "Order{" +
            "ordnum=" + ordnum +
            ", ordamount=" + ordamount +
            ", orderdescription=" + orderdescription +
            ", advanceamount=" + advanceamount +
            ", customer=" + customer +
            ", payments='" + payments + '\'' +
            '}';
    }
}
