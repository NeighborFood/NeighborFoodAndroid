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

public class BuyerOrdersActivityViewModel extends ViewModel {
    private AuthRepository authRepository;
    private OrderRepository orderRepository;
    private MealRepository mealRepository;
    private UserRepository userRepository;

    public BuyerOrdersActivityViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }
    /*
     *  fetches user by id
     */
    public Task<User> getUserById(String id){
        return userRepository.getUserById(id);
    }

    /*
     *  fetches all orders made by a user (as a Buyer).
     */
    public Task<List<Order>> getUserOrders(){
        return orderRepository.getAllOrdersByBuyerId(authRepository.getCurrentUser().getId());
    }
    /*
        fetches meal by id
     */
    public Task<Meal> getMealById(String id){
        return mealRepository.getMealById(id);
    }

}
