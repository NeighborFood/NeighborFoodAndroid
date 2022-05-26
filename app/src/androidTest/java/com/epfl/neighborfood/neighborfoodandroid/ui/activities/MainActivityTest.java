package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);
    @Before
    public void setUp() throws Exception {
        Intents.init();
    }
    @Test
    public void homeButtonLoadsMealList(){
        onView(withId((R.id.navBarHome))).perform(click());
        testRule.getScenario().onActivity(activity ->{
            FragmentManager fragManager = activity.getSupportFragmentManager();
                int count = fragManager.getBackStackEntryCount();
                Fragment frag = fragManager.getFragments().get(count>0?count-1:count);
                assert  frag instanceof MealListFragment;
        });

    }
    @Test
    public void accountButtonLoadsAccountPage(){
        onView(withId((R.id.navBarAccount))).perform(click());
        testRule.getScenario().onActivity(activity ->{
            FragmentManager fragManager = activity.getSupportFragmentManager();
            int count = fragManager.getBackStackEntryCount();
            Fragment frag = fragManager.getFragments().get(count>0?count-1:count);
            assert  frag instanceof AccountFragment;
        });

    }


    @After
    public void tearDown() throws Exception {
        Intents.release();
    }


}
