package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.util.matchers.NthChildOfMatcher;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class VendorProfileAcitivtyTest {
    private static String dummyVendorID = "-1";
    static Intent intent;
    static {
        intent = new Intent(ApplicationProvider.getApplicationContext(), VendorProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userID", dummyVendorID);
        intent.putExtras(bundle);
    }
    @Rule
    public ActivityScenarioRule<VendorProfileActivity> testRule = new ActivityScenarioRule<>(intent);
    @BeforeClass
    public static void setUpApp(){
        NeighborFoodApplication.appContainer = new AppContainerTestImplementation();
        NeighborFoodApplication.appContainer.getAuthRepo().logInWithGoogleAccount(null);
    }
    @Before
    public void setUp() throws Exception {
        Intents.init();
        ((DummyDatabase)DatabaseFactory.getDependency()).reset();
    }

    @After
    public void cleanup() {
        Intents.release();
        ((DummyDatabase)DatabaseFactory.getDependency()).reset();
    }

    @Test
    public void notifyTest(){
        NeighborFoodApplication.appContainer.getAuthRepo().getCurrentUser().setSubscribedIDs(new ArrayList<>());
        onView(withId(R.id.notificationId))
                .perform(click());
        //onView(withId(R.id.notificationId)).check(matches(withTagValue(equalTo(R.drawable.full_notif))));
    }
    @Test
    public void notifyOffTest() {

        ArrayList<String> subIDs = new ArrayList<>();
        subIDs.add(dummyVendorID);
        NeighborFoodApplication.appContainer.getAuthRepo().getCurrentUser().setSubscribedIDs(subIDs);
        onView(withId(R.id.notificationId))
                .perform(click());
        //onView(withId(R.id.notificationId)).check(matches(withTagValue(equalTo(R.drawable.empty_notif))));
    }

    @Test
    public void clickLinkTest() {

        Matcher<Intent> expectedIntent= allOf(hasAction(Intent.ACTION_VIEW),
                hasData("https://facebook.com/"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));

        onView(NthChildOfMatcher.nthChildOf(withId(R.id.SocialLinksGridLayout),0))
                .perform(click());

        intended(expectedIntent);
    }

}
