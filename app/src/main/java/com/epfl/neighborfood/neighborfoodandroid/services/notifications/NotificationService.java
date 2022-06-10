package com.epfl.neighborfood.neighborfoodandroid.services.notifications;

import com.google.android.gms.tasks.Task;

public interface NotificationService {
    enum NotificationTopic {
        MEALPOSTS();
        private final String topicPrefix;

        NotificationTopic() {
            this.topicPrefix = "mealposts-";
        }

        public String getTopicPrefix() {
            return topicPrefix;
        }
    }

    /**
     * Subscribes the current device to receive updates when the given user places a meal
     *
     * @param uid : the unique id of the user to subscribe to
     * @return a completable task
     */
    Task<Void> subscribeToUserMealPosts(String uid);

    /**
     * Unsubscribes the current device to  not receive updates anymore when the given user places a meal
     *
     * @param uid : the unique id of the user to unsubscribe from
     * @return a completable task
     */
    Task<Void> unsubscribeFromUserMealPosts(String uid);

}
