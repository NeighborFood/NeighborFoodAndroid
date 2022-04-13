package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.VendorProfileActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class VendorProfileAcitivtyTest {
    @Rule
    public ActivityScenarioRule<VendorProfileActivity> testRule = new ActivityScenarioRule<>(VendorProfileActivity.class);
    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void cleanup() {
        Intents.release();
    }

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
    @Test
    public void facebookLinkTest(){
        Matcher<Intent> expectedIntent= allOf(hasAction(Intent.ACTION_VIEW),
                                hasData("https://www.facebook.com/gordonramsay"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.facebookId))
                .perform(click());

        intended(expectedIntent);
     }
    @Test
    public void twitterLinkTest(){
        Matcher<Intent> expectedIntent= allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://twitter.com/GordonRamsay"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.TwitterId))
                .perform(click());

        intended(expectedIntent);
    }
    @Test
    public void instagramLinkTest(){
        Matcher<Intent> expectedIntent= allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://www.instagram.com/gordongram"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(withId(R.id.instagramId))
                .perform(click());

        intended(expectedIntent);
    }
}
