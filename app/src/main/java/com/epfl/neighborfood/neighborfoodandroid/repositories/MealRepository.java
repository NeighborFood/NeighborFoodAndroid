package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseSingleton;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

public class MealRepository {
    private final static String mealsDataCollectionPath = "Meals";

    public MealRepository() {
    }

    /**
     * fetches the meal corresponding to an ID
     *
     * @param id meal id
     * @return Task containg the fetched meal
     */
    public Task<Meal> getMealById(String id) {
        if (id == null) {
            return Tasks.forException(new IllegalArgumentException("The meal ID cannot be null"));
        }
        return DatabaseSingleton.getDependency().fetch(mealsDataCollectionPath, id).continueWith(task -> {
            if (task.isSuccessful()) {
                return task.getResult().toModel(Meal.class);
            }
            return null;
        });
    }

    /**
     * sends a request to post a meal
     *
     * @param meal the meal to post
     * @return the task containing mealId that may complete, fails if the argument is null, or if the database is unreachable
     */
    public Task<String> postMeal(Meal meal) {
        if (meal == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot post a null meal"));
        }
        //We first post the meal to the database,
        return DatabaseSingleton.getDependency().add(mealsDataCollectionPath, meal)
                .continueWithTask(task -> {
                            meal.setMealId(task.getResult());
                            // and once that is done (and we get the corresponding id of the meal),
                            return DatabaseSingleton.getDependency().

                                    // we need to update the mealId field stored in the database
                                            set(mealsDataCollectionPath, task.getResult(), meal).continueWith(t -> task.getResult());
                        }
                );
    }


}
