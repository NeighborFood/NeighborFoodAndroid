package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrderTest {
    private Meal meal;
    private Date orderDate;
    private boolean status;

    @Before
    public void initTestVariables() {
        orderDate = new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime();
        status = true;
    }
    @Test
    public void testGetMealId() {
        Order order = new Order("3","2",orderDate,true,"3","4");
        order.setMealId("1");
        assertEquals(order.getMealId(),"1");
    }
    @Test
    public void testGetOrderId() {
        Order order = new Order("2","3",orderDate,true,"3","4");
        order.setOrderId("1");
        assertEquals(order.getOrderId(),"1");
    }
    @Test
    public void testGetVendorId() {
        Order order = new Order("3","3",orderDate,true,"2","4");
        order.setVendorId("1");
        assertEquals(order.getVendorId(),"1");
    }
    @Test
    public void testGetBuyerId() {
        Order order = new Order("3","3",orderDate,true,"2","4");
        order.setBuyerId("1");
        assertEquals(order.getBuyerId(),"1");
    }
    @Test
    public void testGetOrderDate() {
        Order order = new Order("3","3",orderDate,true,"3","4");
        //order.setOrderDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 2).getTime());
//        assertEquals(order.getOrderDate().getTime(),new GregorianCalendar(2022, Calendar.FEBRUARY, 2).getTime());
    }
    @Test
    public void testStatus() {
        Order order = new Order("3","3",orderDate,false,"3","4");
        order.setStatus(true);
        assertEquals(order.getStatus(), true);
    }

}
