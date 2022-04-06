package com.epfl.neighborfood.neighborfoodandroid.models;

import com.epfl.neighborfood.neighborfoodandroid.R;

public enum Allergen {
    CELERY (R.drawable.celery, "celery"),
    MILK (R.drawable.milk, "milk"),
    FISH (R.drawable.fish, "fish"),
    CHEESE (R.drawable.cheese, "cheese"),
    GLUTEN (R.drawable.gluten, "gluten"),
    HONEY (R.drawable.honey, "honey"),
    LOBSTER (R.drawable.lobster, "Lobster"),
    SOY (R.drawable.soy, "Soy"),
    EGGS (R.drawable.eggs, "Eggs"),
    CHOCOLATE (R.drawable.chocolate, "Chocolate");

    private final int idIcon;   // in kilograms
    private final String label; // in meters

    public int getId() {
        return idIcon;
    }

    public String getLabel() {
        return label;
    }

    Allergen(int id, String label) {
        this.idIcon = id;
        this.label = label;
    }

}