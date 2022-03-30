package com.epfl.neighborfood.neighborfoodandroid.models;

import com.epfl.neighborfood.neighborfoodandroid.R;

public enum Allergen {
    CELERY (R.id.CeleryIcon, "celery"),
    MILK (R.id.MilkIcon, "milk"),
    FISH (R.id.FishIcon, "fish"),
    CHEESE (R.id.CheeseIcon, "cheese"),
    GLUTEN (R.id.GlutenIcon, "gluten"),
    HONEY (R.id.HoneyIcon, "honey"),
    LOBSTER (R.id.LobsterIcon, "Lobster"),
    SOY (R.id.SoyIcon, "Soy"),
    EGGS (R.id.EggsIcon, "Eggs"),
    CHOCOLATE (R.id.ChocolateIcon, "Chocolate");

    private final Object id;   // in kilograms
    private final String label; // in meters

    public Object getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    Allergen(Object id, String label) {
        this.id = id;
        this.label = label;
    }

}