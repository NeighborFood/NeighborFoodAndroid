package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MealListViewModel extends ViewModel {
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;

    public MealListViewModel(MealRepository mealRepository, OrderRepository orderRepository, AuthRepository authRepository) {
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.authRepository = authRepository;
    }

    public Task<List<Order>> getAllUnassignedOrders() {
        return orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned).addOnFailureListener(System.out::println);
    }

    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}