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
    private String mealVendor;

    @Before
    public void initTestVariables() {
        meal = new Meal("Tofu","Yumi tofu","Plant based proteins",5);
        orderDate = new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime();
        status = true;
        mealVendor = "vendor";
    }
    @Test
    public void testGetMeal() {
        Order order = new Order(meal,orderDate,status,mealVendor);
        assertEquals(order.getMeal().getName(),meal.getName());
    }
    @Test
    public void testGetOrder() {
        Order order = new Order(meal,orderDate,status,mealVendor);
        assertEquals(order.getOrderDate().getTime(),orderDate.getTime());
    }
    @Test
    public void testStatus() {
        Order order = new Order(meal,orderDate,status,mealVendor);
        assertEquals(order.getOrder().isStatus(), order.isStatus());
    }
    @Test
    public void testGetMealVendor() {
        Order order = new Order(meal,orderDate,status,mealVendor);
        assertEquals(order.getMealVendor(), mealVendor);
    }
}
