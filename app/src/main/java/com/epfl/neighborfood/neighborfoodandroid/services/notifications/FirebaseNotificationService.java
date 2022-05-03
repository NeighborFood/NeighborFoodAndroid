package com.epfl.neighborfood.neighborfoodandroid.services.notifications;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
/**
 * Firebase implementation of the Notification Service
 */
public class FirebaseNotificationService  extends FirebaseMessagingService implements NotificationService {

    @Override
    public Task<Void> subscribeToUserMealPosts(String uid) {
        if(uid == null){
            return Tasks.forException(new IllegalArgumentException("Invalid Uid"));
        }
        return FirebaseMessaging.getInstance()
                .subscribeToTopic(NotificationTopic.MEALPOSTS.getTopicPrefix()+uid)
                .continueWith(task -> null);
    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

    }

    @Override
    public Task<Void> unsubscribeFromUserMealPosts(String uid) {
        return FirebaseMessaging.getInstance()
                .unsubscribeFromTopic(NotificationTopic.MEALPOSTS.getTopicPrefix()+uid)
                .continueWith(task -> null);
    }

    @Override
    public void onNewToken(String token){

    }
}
