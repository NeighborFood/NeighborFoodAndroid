package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MealListViewModel extends ViewModel {
    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    public MealListViewModel(MealRepository mealRepository, OrderRepository orderRepository){
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
    }
    public Task<List<Pair<Order, Meal>>> getAllUnassignedMeals(){
        ArrayList<Pair<Order,Meal>> res = new ArrayList<>();
        return orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned).c(to->
        {
            if(to.isSuccessful()) {
                System.out.println("zab"+ to.getResult().size());
                for (Order order : to.getResult()) {
                    mealRepository.getMealById(order.getMealId()).addOnSuccessListener(meal -> {
                        res.add(Pair.of(order, meal));
                    });
                }
            }
            return res;
        });
    }
    public List<Pair<Order, Meal>> getAllUnassignedOrders() {
        ArrayList<Pair<Order, Meal>> res = new ArrayList<>();
        orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned).addOnSuccessListener(orders -> {
            System.out.println("meeeeeeeeeeh" +orders.size());
            mealRepository.getMealById(orders.get(0).getMealId()).addOnSuccessListener(meal -> {
                System.out.println(meal.getMealId()+"--1------------");
                res.add(Pair.of(orders.get(0), meal));
            }).addOnFailureListener(System.out::println);
            /*for (Order order : orders) {
                System.out.println(order.getMealId()+"--0------------");
                mealRepository.getMealById(order.getMealId()).addOnSuccessListener(meal -> {
                    System.out.println(meal.getMealId()+"--1------------");
                    res.add(Pair.of(order, meal));
                }).addOnFailureListener(System.out::println);
            }*/
        });
        System.out.println(res.size()+"--1------------");
        return res;
    }
}