package com.epfl.neighborfood.neighborfoodandroid;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity.EXTRA_MESSAGE;

import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void intentOnButtonClickTest() {

        Intents.init();
        String inputString = "NeighborFoodTeam";
        String packageName = "com.epfl.neighborfood.neighborfoodandroid";

        onView(withId(R.id.mainName)).perform(replaceText(inputString));
        onView(withId(R.id.mainGo)).perform(click());


        intended(toPackage(packageName));
        intended(hasExtra(EXTRA_MESSAGE, inputString ));

        Intents.release();

    }



}
