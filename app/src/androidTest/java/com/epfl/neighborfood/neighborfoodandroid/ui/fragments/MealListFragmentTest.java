package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import android.os.Bundle;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class MealListFragmentTest {

    private final String PACKAGENAME = "com.epfl.neighborfood.neighborfoodandroid";
    private FragmentScenario scenario;

    @Before
    public void setUp() throws Exception {
        Intents.init();
        scenario = FragmentScenario.launchInContainer(MealListFragment.class,null);
    }

    @Test
    public void testFirstMealIsClickable() {
        onData(anything()).inAdapterView(withId(R.id.mealListView)).atPosition(0).perform(click());
    }

    // Can remove when removing the corresponding button
    @Test
    public void testClickMessages() {
        //onView(withId(R.id.messagesButton)).perform(click());
    }



    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}