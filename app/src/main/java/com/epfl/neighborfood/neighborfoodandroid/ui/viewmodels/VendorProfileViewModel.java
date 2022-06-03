package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;
import com.google.android.gms.tasks.Task;

public class VendorProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final AuthRepository authRepository;

    public VendorProfileViewModel(UserRepository userRepository,AuthRepository authRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.authRepository = authRepository;
    }

    /** Subscribes to vendor mealPosts
     * @param vendor the id of the vendor
     * @return the completable task
     */
    public Task<Void> subscribeToVendor(User vendor){
        return notificationService.subscribeToUserMealPosts(vendor.getId()).continueWithTask(t->{
            vendor.setNumberSubscribers(vendor.getNumberSubscribers()+1);
            return userRepository.updateUser(vendor);
        }).continueWithTask(t->{
            User current = getCurrentUser();
            current.getSubscribedIDs().add(vendor.getId());
            return userRepository.updateUser(current);
        });
    }
    /** Unsubscribes from vendor mealPosts
     * @param vendor the id of the vendor
     * @return the completable task
     */
    public Task<Void> unsubscribeFromVendor(User vendor){
        return notificationService.unsubscribeFromUserMealPosts(vendor.getId()).continueWithTask(t->{
            vendor.setNumberSubscribers(vendor.getNumberSubscribers()-1);
            return userRepository.updateUser(vendor);
        }).continueWithTask(t->{
            User current = getCurrentUser();
            if(current.getSubscribedIDs().remove(vendor.getId())){
                return userRepository.updateUser(current);
            }
            return t;
        });
    }
    public Task<User> getUserByID(String userID){
        return userRepository.getUserById(userID);
    }

    public User getCurrentUser(){
        return authRepository.getCurrentUser();
    }
}