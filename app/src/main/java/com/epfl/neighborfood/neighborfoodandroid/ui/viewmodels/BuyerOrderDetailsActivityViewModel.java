package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class BuyerOrderDetailsActivityViewModel extends ViewModel {
    private AuthRepository authRepository;
    private OrderRepository orderRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;

    public BuyerOrderDetailsActivityViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }
    /*
        fetches user by id
     */
    public Task<User> getUserById(String id){
        return userRepository.getUserById(id);
    }
    /*
        fetches order by id
     */
    public Task<Order> getOrderById(String id){
        return orderRepository.getOrderById(id);
    }
    /*
        fetches meal by id
     */
    public Task<Meal> getMealById(String id){
        return mealRepository.getMealById(id);
    }

}