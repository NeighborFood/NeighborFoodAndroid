package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;
import com.google.android.gms.tasks.Task;

public class VendorProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public VendorProfileViewModel(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    /** Subscribes to vendor mealPosts
     * @param vendorID the id of the vendor
     * @return the completable task
     */
    public Task<Void> subscribeToVendor(String vendorID){
        return notificationService.subscribeToUserMealPosts(vendorID);
    }
    /** Unsubscribes from vendor mealPosts
     * @param vendorID the id of the vendor
     * @return the completable task
     */
    public Task<Void> unsubscribeFromVendor(String vendorID){
        return notificationService.unsubscribeFromUserMealPosts(vendorID);
    }
}