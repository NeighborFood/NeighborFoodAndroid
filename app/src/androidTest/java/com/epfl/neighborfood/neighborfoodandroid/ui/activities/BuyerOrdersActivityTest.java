package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.getIntents;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertEquals;

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

public class BuyerOrdersActivityTest {
    @Rule
    public ActivityScenarioRule<BuyerOrdersActivity> testRule = new ActivityScenarioRule<>(BuyerOrdersActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void cleanup() {
        Intents.release();
    }

    /*@Test
    public void testTransitionToOrderDetailsActivity(){
        Meal meal = new Meal("Poulet au miel","Un délicieux poulet au miel",
                "Vous ne pourrez pas résister à ce savoureux poulet",R.drawable.poulet);
        Order order = new Order( meal, new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime(),
                false,"Vendor A");
        onData(anything()).inAdapterView(withId(R.id.order_list_view)).atPosition(0).
                perform(click());
        intended(hasComponent(BuyerOrderDetailsActivity.class.getName()));
        Order intentOrder = (Order) getIntents().get(0).getSerializableExtra("order");
        assertEquals(order.getOrderId(),intentOrder.getBuyerId());
    }*/
}
