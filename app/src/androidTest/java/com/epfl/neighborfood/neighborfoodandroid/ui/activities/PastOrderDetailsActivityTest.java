package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Activity;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(AndroidJUnit4.class)

public class PastOrderDetailsActivityTest {
    Meal meal = new Meal("Poulet au miel","Un délicieux poulet au miel",
            "Vous ne pourrez pas résister à ce savoureux poulet",R.drawable.poulet);
    Order order = new Order( meal, new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime(),
            false,"Vendor A");
    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void cleanup() {
        Intents.release();
    }
    @Test
    public void TestTransitionToVendorActivity(){
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), PastOrderDetailsActivity.class);
        intent.putExtra("order",order);
        ActivityScenario<PastOrderDetailsActivity> scenario = ActivityScenario.launch(intent);
        onView(withId(R.id.go_vendor_profile_id)).perform(click());
        intended(hasComponent(VendorProfileActivity.class.getName()));
        scenario.close();
    }
    @Test
    public void TestOrderAttributesAreUpdated(){
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), PastOrderDetailsActivity.class);
        intent.putExtra("order",order);
        ActivityScenario<PastOrderDetailsActivity> scenario = ActivityScenario.launch(intent);
        onView(withId(R.id.go_vendor_profile_id)).check(matches(withText(order.getMealVendor())));
        onView(withId(R.id.mealName)).check(matches(withText(order.getMeal().getName())));
        onView(withId(R.id.mealDesc)).check(matches(withText(order.getMeal().getShortDescription())));
        scenario.close();
    }
}
