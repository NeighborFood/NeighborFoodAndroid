package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity.EXTRA_MESSAGE;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DisplayMessageActivityTest {
    @Test
    public void greetingMessageTest(){
        String intentString = "NeighborFoodTeam";
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, intentString);

        ActivityScenario<DisplayMessageActivity> scenario = ActivityScenario.launch(intent);
        onView(ViewMatchers.withId(R.id.textView)).check(matches(withText(intentString)));
        scenario.close();

    }




}
