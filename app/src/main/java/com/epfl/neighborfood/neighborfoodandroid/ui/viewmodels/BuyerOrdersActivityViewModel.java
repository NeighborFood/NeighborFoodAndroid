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
    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public BuyerOrdersActivityViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    /**
     * fetches user by id
     *
     * @param id id of user
     * @return a task containing the User fetched
     */
    public Task<User> getUserById(String id) {
        return userRepository.getUserById(id);
    }

    /**
     * fetches all orders made by the authorised user as a buyer
     *
     * @return a task containing the list of orders fetched
     */
    public Task<List<Order>> getUserOrders() {
        return orderRepository.getAllOrdersByBuyerId(authRepository.getCurrentUser().getId());
    }

    /**
     * fetches meal by id
     *
     * @param id id of meal
     * @return a task containing the meal fetched
     */
    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

}
