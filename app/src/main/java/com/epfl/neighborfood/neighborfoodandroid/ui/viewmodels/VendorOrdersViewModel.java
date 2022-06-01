package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class VendorOrdersViewModel extends ViewModel {
    private AuthRepository authRepository;
    private OrderRepository orderRepository;
    private MealRepository mealRepository;


    public VendorOrdersViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository) {
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
    }

    /*
     *  fetches all orders made by a user (as a Vendor).
     */
    public Task<List<Order>> getVendorOrders() {
        return orderRepository.getAllOrdersByVendorId(authRepository.getCurrentUser().getId());
    }

    /*
        fetches meal by id
     */
    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

}