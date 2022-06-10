package com.epfl.neighborfood.neighborfoodandroid.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
        meal = new Meal("Tofu","Yumi tofu","",new ArrayList<>(),orderDate);
        status = true;
    }
    @Test
    public void testGetMealId() {
        Order order = new Order("3","2",orderDate,OrderStatus.unassigned,"3","4",null);
        order.setMealId("1");
        assertEquals(order.getMealId(),"1");
    }
    @Test
    public void testGetOrderId() {
        Order order = new Order("2","3",orderDate,OrderStatus.unassigned,"3","4",null);
        order.setOrderId("1");
        assertEquals(order.getOrderId(),"1");
    }
    @Test
    public void testGetVendorId() {
        Order order = new Order("3","3",orderDate,OrderStatus.unassigned,"2","4",null);
        order.setVendorId("1");
        assertEquals(order.getVendorId(),"1");
    }
    @Test
    public void testGetBuyerId() {
        Order order = new Order("3","3",orderDate,OrderStatus.unassigned,"2","4",null);
        order.setBuyerId("1");
        assertEquals(order.getBuyerId(),"1");
    }
    @Test
    public void testGetOrderDate() {
        Order order = new Order("3","3",orderDate,OrderStatus.unassigned,"3","4",null);
        //order.setOrderDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 2).getTime());
//        assertEquals(order.getOrderDate().getTime(),new GregorianCalendar(2022, Calendar.FEBRUARY, 2).getTime());
    }
    @Test
    public void testStatus() {
        Order order = new Order("3","3",orderDate,OrderStatus.unassigned,"3","4",null);
        order.setOrderStatus(OrderStatus.assigned);
        assertEquals(order.getOrderStatus(), OrderStatus.assigned);
    }

}
