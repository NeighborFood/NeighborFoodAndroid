package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.google.android.gms.tasks.Task;

public class MealViewModel extends ViewModel {
    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private AuthRepository authRepository;

    public MealViewModel(OrderRepository orderRepo, MealRepository mealRepo, UserRepository userRepo, AuthRepository authRepo) {
        orderRepository = orderRepo;
        mealRepository = mealRepo;
        userRepository = userRepo;
        authRepository = authRepo;
    }

    /*
       fetches order by id
    */
    public Task<Order> getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }

    /*
       fetches meal by id
    */
    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

    /*
       fetches user by id
    */
    public Task<User> getVendorById(String id) {
        return userRepository.getUserById(id);
    }

    /*
        updates the order with its new attributes when he gets assigned
     */
    public Task<Void> assignOrder(Order order) {
        order.setBuyerId(getCurrentUser().getId());
        order.setOrderStatus(OrderStatus.assigned);
        return orderRepository.updateOrder(order);
    }

    /*
       fetches current user.
    */
    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
