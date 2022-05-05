package com.epfl.neighborfood.neighborfoodandroid.services.notifications;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.epfl.neighborfood.neighborfoodandroid.AppContainer;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MealActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Firebase implementation of the Notification Service
 */
public class FirebaseNotificationService  extends FirebaseMessagingService implements com.epfl.neighborfood.neighborfoodandroid.services.notifications.NotificationService {

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
        createNotificationChannel();

        String mealId = remoteMessage.getData().get("mealId");
        String vendorID = remoteMessage.getData().get("vendorID");
        AppContainer appContainer = ((NeighborFoodApplication) this.getApplication()).getAppContainer();
        /*
        I couldn't fetch the data from the database that's why I had to remove the following two lines

        User vendor = appContainer.getUserRepo().getUserById(vendorID).getResult();

        AtomicReference<Meal> meal = new AtomicReference<Meal>();
        appContainer.getMealRepo().getMealById(mealId).addOnSuccessListener(result->{
          meal.set(result);
        });
        */
        User vendor = new User("id1", "email@epfl.ch", "gordon"," ramsey");
        Meal meal = new Meal("paella","delicious paella","delicious paella",R.drawable.fondue);

        //create intent when clicking on notification which redirects to the mealActivity
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra("name", meal.getName());
        intent.putExtra("shortDes", meal.getShortDescription());
        intent.putExtra("longDes", meal.getLongDescription());
        intent.putExtra("imageid", meal.getImageId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,getString(R.string.channel_id))
                .setSmallIcon(R.drawable.full_notif)
                .setContentTitle("New meal available!")
                .setContentText(meal.getName() + " have been prepared by " + vendor.getFullName())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        new Handler(Looper.getMainLooper()).post(() -> {
            notificationManager.notify(0,builder.build());
        });
    }

    @Override
    public Task<Void> unsubscribeFromUserMealPosts(String uid) {
        return FirebaseMessaging.getInstance()
                .unsubscribeFromTopic(NotificationTopic.MEALPOSTS.getTopicPrefix()+uid)
                .continueWith(task -> null);
    }

    /*
    Create the channel for the notifications
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
