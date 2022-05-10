package com.epfl.neighborfood.neighborfoodandroid.repositories;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {
    private final static String mealsDataCollectionPath = "Meals";

    public MealRepository() {
    }
    public Task<Meal> getMealById(String id){
        if (id == null) {
            return Tasks.forException(new IllegalArgumentException("The meal ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetch(mealsDataCollectionPath, id).continueWith(task -> {
            if (task.isSuccessful()) {
                return task.getResult().toModel(Meal.class);
            }
            return null;
        });
    }
    public Task<Void> postMeal(Meal meal){
        if (meal == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot post a null meal"));
        }
        return DatabaseFactory.getDependency().add(mealsDataCollectionPath,meal)
                .continueWithTask(task ->
                        DatabaseFactory.getDependency().
                                set(mealsDataCollectionPath,task.getResult(),meal.copyWithId(task.getResult())));
    }
    public Task<List<Meal>> getAllMeals(){
        return DatabaseFactory.getDependency().fetchAll(mealsDataCollectionPath).continueWith(t->{
            ArrayList<Meal> res = new ArrayList<>();
            if(t.isSuccessful()){
                for (DocumentSnapshot m: t.getResult().getDocuments()) {
                    res.add(m.toModel(Meal.class) );
                }
            }
            return res;
        });
    }
}
