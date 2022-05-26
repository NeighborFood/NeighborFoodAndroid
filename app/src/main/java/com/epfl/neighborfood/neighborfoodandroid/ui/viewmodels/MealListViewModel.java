package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MealListViewModel extends ViewModel {
    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    public MealListViewModel(MealRepository mealRepository, OrderRepository orderRepository){
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
    }
    public Task<List<Order>> getAllUnassignedOrders(){
        return orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned);
    }

    public Task<Meal> getMealById(String id){
        return mealRepository.getMealById(id);
    }

}