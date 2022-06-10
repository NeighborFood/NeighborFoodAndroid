package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.epfl.neighborfood.neighborfoodandroid.models.PickupLocation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.MealRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.OrderRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.location.LocationService;
import com.google.android.gms.tasks.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MealListViewModel extends ViewModel {

    public enum Ordering {
        PRICE(priceComparator), DISTANCE(distanceComparator);
        private final Comparator<Order> comparator;

        Ordering(Comparator<Order> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Order> getComparator() {
            return comparator;
        }
    }

    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;
    private final LocationService locationService;
    private final LiveData<PickupLocation> pickupLocationLiveData;
    private Ordering ordering;
    private final MutableLiveData<List<Order>> ordersLiveData;
    private static PickupLocation userLocation;

    private final static Comparator<Order> distanceComparator = (o1, o2) -> {
        if (userLocation != null) {
            double distance1 = PickupLocation.distanceBetweenLocations(userLocation, o1.getLocation());
            double distance2 = PickupLocation.distanceBetweenLocations(userLocation, o2.getLocation());
            return (int) (distance1 - distance2);
        }
        return 0;
    };
    private final static Comparator<Order> priceComparator = (o1, o2) -> (int) (o1.getPrice() - o2.getPrice());

    public MealListViewModel(MealRepository mealRepository, OrderRepository orderRepository, AuthRepository authRepository, LocationService locationService) {
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.authRepository = authRepository;
        this.locationService = locationService;
        ordersLiveData = new MutableLiveData<>();
        pickupLocationLiveData = locationService.getPickupLocationLiveData();
        pickupLocationLiveData.observeForever(location -> {
            userLocation = location;
            getAllUnassignedOrders();

        });
        ordering = Ordering.PRICE;
    }

    /**
     * fetches User location's live data
     *
     * @return the live date of the user location
     */
    public LiveData<PickupLocation> getUserLocation() {
        locationService.getDeviceLocation();
        return pickupLocationLiveData;
    }

    /**
     * fetches all the unassigned orders
     *
     * @return live data of the fetched orders
     */
    public LiveData<List<Order>> getAllUnassignedOrders() {
        orderRepository.getAllOrdersMatchingStatus(OrderStatus.unassigned).addOnSuccessListener(l -> {
            if (l != null) {
                ordersLiveData.postValue(l);
                reorderList();
            }
        });
        return ordersLiveData;
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
     * fetches current authenticated user
     *
     * @return the current User fetched
     */
    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    /**
     * sets the ordering for the fetched orders
     *
     * @param ordering ordering to be set
     */
    public void setOrdering(int ordering) {
        if (ordering < Ordering.values().length && ordering >= 0) {
            this.ordering = Ordering.values()[ordering];
            reorderList();
        }
    }

    /**
     * fetches the ordering index
     *
     * @return ordering index
     */
    public int getOrderingIndex() {
        return ordering.ordinal();
    }

    /**
     * reorders the orders list fetched
     */
    private void reorderList() {
        List<Order> newList = ordersLiveData.getValue();
        if (newList == null) {
            return;
        }
        Collections.sort(newList, ordering.getComparator());

        ordersLiveData.postValue(newList);
    }
}
