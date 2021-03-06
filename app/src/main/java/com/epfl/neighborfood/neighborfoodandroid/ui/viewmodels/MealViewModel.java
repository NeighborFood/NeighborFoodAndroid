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
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public MealViewModel(OrderRepository orderRepo, MealRepository mealRepo, UserRepository userRepo, AuthRepository authRepo) {
        orderRepository = orderRepo;
        mealRepository = mealRepo;
        userRepository = userRepo;
        authRepository = authRepo;
    }

    /**
     * fetches order by id
     *
     * @param id id of meal
     * @return a task containing the order fetched
     */
    public Task<Order> getOrderById(String id) {
        return orderRepository.getOrderById(id);
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

    /**
     * updates the order with its new attributes when he gets assigned
     *
     * @param order order to be assigned
     * @return task of the assigning of the order
     */
    public Task<Void> assignOrder(Order order) {
        order.setBuyerId(getCurrentUser().getId());
        order.setOrderStatus(OrderStatus.assigned);
        return orderRepository.updateOrder(order);
    }

    /**
     * fetches current authenticated user
     *
     * @return the current User fetched
     */
    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
