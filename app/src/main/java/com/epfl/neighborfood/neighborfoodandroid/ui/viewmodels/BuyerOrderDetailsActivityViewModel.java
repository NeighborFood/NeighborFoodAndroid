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

public class BuyerOrderDetailsActivityViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public BuyerOrderDetailsActivityViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository, UserRepository userRepository) {
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
     * fetches order by id
     * @param id id of order
     * @return a task containing the order fetched
     */
    public Task<Order> getOrderById(String id) {
        return orderRepository.getOrderById(id);
    }

    /**
     * fetches meal by id
     * @param id id of meal
     * @return a task containing the meal fetched
     */
    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

    /**
     * updates the order status to confirmed
     *
     * @param order order to be confirmed
     * @return task of the update
     */
    public Task<Void> confirmOrder(Order order) {
        order.setOrderStatus(OrderStatus.finished);
        return orderRepository.updateOrder(order);
    }

}