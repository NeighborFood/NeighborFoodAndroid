package com.epfl.neighborfood.neighborfoodandroid.services.notification;

import com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class NotificationServiceTestImplementation implements NotificationService {
    @Override
    public Task<Void> subscribeToUserMealPosts(String uid) {
        return Tasks.forResult(null);
    }

    @Override
    public Task<Void> unsubscribeFromUserMealPosts(String uid) {
        return Tasks.forResult(null);
    }
}
