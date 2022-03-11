package com.epfl.neighborfood.neighborfoodandroid;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;

import org.junit.Rule;


public class PlaceMealMenuTest {
    @Rule
    public ActivityScenarioRule<PlaceMealActivity> testRule = new ActivityScenarioRule<>(PlaceMealActivity.class);

}
