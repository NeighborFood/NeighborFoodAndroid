package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.ImageUtil;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;

public class    PlaceMealViewModel extends ViewModel {
    private final MealRepository mealRepository;
    private final AuthRepository authRepository;
    private final OrderRepository orderRepository;

    public PlaceMealViewModel(MealRepository mealRepository, AuthRepository authRepository, OrderRepository orderRepository) {
        this.mealRepository = mealRepository;
        this.authRepository = authRepository;
        this.orderRepository = orderRepository;
    }

    /** Requests to place a meal on the database
     * @param meal : the meal to post
     * @return the completable task containing mealId
     */
    public Task<String> placeMeal(Meal meal,String imagePath){
        meal.setVendorID(authRepository.getAuthUser().getId());
        return ImageUtil.uploadImage(imagePath).continueWithTask(imageUploadTask->{
            meal.setImageUri(imageUploadTask.getResult());
            return mealRepository.postMeal(meal);
        });
    }
    /** Requests to create an unassigned order on the database
     * @param mealId : the mealId corresponding to the new order
     * @return the completable task containing orderId
     */
    public Task<String> createOrder(String mealId, PickupLocation location){
        Order order = new Order();
        order.setMealId(mealId);
        order.setVendorId(authRepository.getAuthUser().getId());
        order.setBuyerId("");
        order.setOrderStatus(OrderStatus.unassigned);
        order.setLocation(location);
        order.setOrderDate(Calendar.getInstance().getTime());
        return orderRepository.makeOrder(order);
    }
}
