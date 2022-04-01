package com.epfl.neighborfood.neighborfoodandroid.ui.layouts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;

import static org.hamcrest.Matchers.equalTo;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.VendorProfileActivity;

import org.junit.Rule;
import org.junit.Test;

public class VendorProfileAcitivtyTest {
    @Rule
    public ActivityScenarioRule<VendorProfileActivity> testRule = new ActivityScenarioRule<>(VendorProfileActivity.class);
    @Test
    public void subscribeTest(){
        onView(withId(R.id.SubscribeId))
                .perform(click());
        onView(withId(R.id.SubscribeId)).check(matches(withTagValue(equalTo(R.drawable.full_heart))));
    }
    @Test
    public void unsubscribeTest(){
        onView(withId(R.id.SubscribeId))
                .perform(click()).perform(click());
        onView(withId(R.id.SubscribeId)).check(matches(withTagValue(equalTo(R.drawable.empty_heart))));
    }
    @Test
    public void notifyTest(){
        onView(withId(R.id.notificationId))
                .perform(click());
        onView(withId(R.id.notificationId)).check(matches(withTagValue(equalTo(R.drawable.full_notif))));
    }
    @Test
    public void notifyOffTest(){
        onView(withId(R.id.notificationId))
                .perform(click()).perform(click());
        onView(withId(R.id.notificationId)).check(matches(withTagValue(equalTo(R.drawable.empty_notif))));
    }
}
