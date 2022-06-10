package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MealListViewModel extends ViewModel implements LocationListener {
    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public enum Ordering{
        DISTANCE(distanceComparator),PRICE(priceComparator);
        private final Comparator<Order> comparator;
        Ordering(Comparator<Order> comparator){
            this.comparator = comparator;
        }
        public Comparator<Order> getComparator(){
            return comparator;
        }
    }
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;
    private Ordering ordering;
    private final MutableLiveData<List<Order>> ordersLiveData;
    private Location location;
    private final static Comparator<Order> distanceComparator= (o1, o2) -> {
        /*if(location != null){
            int i = (int) (location.distanceTo(location) - location.distanceTo(location));
            return i;
        }*/
        return 0;
    };
    private final static Comparator<Order> priceComparator = (o1, o2) -> (int) (o1.getPrice() - o2.getPrice());

    public MealListViewModel(MealRepository mealRepository, OrderRepository orderRepository, AuthRepository authRepository) {
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.authRepository = authRepository;
        ordersLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Order>> getAllUnassignedOrders() {
        orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned).addOnSuccessListener(l->{
            if(l != null){
                ordersLiveData.postValue(l);
            }
        });
        return ordersLiveData;
    }

    public Task<Meal> getMealById(String id) {
        return mealRepository.getMealById(id);
    }

    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
    public void setOrdering(int ordering){
        System.out.println(ordering);
        if(ordering<Ordering.values().length && ordering>=0 ){
            this.ordering = Ordering.values()[ordering];
            System.out.println(this.ordering);
            reorderList();
        }
    }
    private void reorderList(){
        List<Order> newList = ordersLiveData.getValue();
        if(newList == null){
            return;
        }
        Collections.sort(newList,ordering.getComparator());

        ordersLiveData.postValue(newList);
    }
}