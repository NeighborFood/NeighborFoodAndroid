package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.stream.Collectors;

public class VendorOrdersViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;
    private final MealRepository mealRepository;


    public VendorOrdersViewModel(AuthRepository authRepository, OrderRepository orderRepository, MealRepository mealRepository) {
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
    }

    /**
     * fetches all the orders made by the authenticated user as a vendor
     *
     * @return task containing the orders fetched
     */
    public Task<List<Order>> getVendorOrders() {
        return orderRepository.getAllOrdersByVendorId(authRepository.getCurrentUser().getId());
    }

    /**
     * fetches all the unassigned orders made by the authenticated user as a vendor
     *
     * @return task containing the orders fetched
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Task<List<Order>> getUnassignedVendorOrders() {
        return orderRepository.getAllOrdersByVendorId(authRepository.getCurrentUser().getId()).continueWith(t -> t.getResult().stream().filter(o -> o.getOrderStatus().equals(OrderStatus.unassigned)).collect(Collectors.toList()));
    }

    /**
     * fetches all the orders waiting for confirmation made by the authenticated user as a vendor
     *
     * @return task containing the orders fetched
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Task<List<Order>> getWaitingVendorOrders() {
        return orderRepository.getAllOrdersByVendorId(authRepository.getCurrentUser().getId()).continueWith(t -> t.getResult().stream().filter(o -> o.getOrderStatus().equals(OrderStatus.assigned)).collect(Collectors.toList()));
    }

    /**
     * fetches all the completed orders made by the authenticated user as a vendor
     *
     * @return task containing the orders fetched
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Task<List<Order>> getFinishedVendorOrders() {
        return orderRepository.getAllOrdersByVendorId(authRepository.getCurrentUser().getId()).continueWith(t -> t.getResult().stream().filter(o -> o.getOrderStatus().equals(OrderStatus.finished)).collect(Collectors.toList()));
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