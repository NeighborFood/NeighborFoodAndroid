package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/**
 * Entry point to manipulate Users in the database
 */
public class UserRepository {
    private final static String userDataCollectionPath = "Users";

    /**
     * Creates an instance of the User Repository
     */
    public UserRepository(){
    }

    /** Fetches a user from the database, by the user's id
     * @param id : the id of the user
     * @return the task that will contain the result of the query
     */
    public Task<User> getUserById(String id) {
        //if the id is null, return a failed task, and give a bit explanation on the failure
        if (id == null) {
            return Tasks.forException(new IllegalArgumentException("The user ID cannot be null"));
        }
        return  DatabaseFactory.getDependency().fetch(userDataCollectionPath, id).continueWith(task -> {
            if (task.isSuccessful()) {
                return task.getResult().toModel(User.class);
            }
            return null;
        });
    }

    /** Updates the user in the database, the user is created if it does not exist on the db
     * @param user the user that will be added to the database
     * @return the task that succeeds or fails
     */
    public Task<Void> updateUser(User user){
        if(user == null){ // return a failed task if the given user is not valid
            return Tasks.forException(new IllegalArgumentException("Provided user is null"));
        }
        //push the user to the database
        return  DatabaseFactory.getDependency().set(userDataCollectionPath,user.getId(),user).continueWith(task->null);
    }

    /** Deletes a user from the database
     * @param id : the user to Delete
     * @return the task that may succeed or fail
     */
    public Task<Void> deleteUser(String id){
        if (id == null) {
            return Tasks.forException(new IllegalArgumentException("The user ID cannot be null"));
        }
        return DatabaseFactory.getDependency().delete(userDataCollectionPath, id);
    }

}
