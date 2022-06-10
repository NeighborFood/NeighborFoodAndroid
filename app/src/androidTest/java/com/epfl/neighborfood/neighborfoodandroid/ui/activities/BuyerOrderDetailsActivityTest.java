package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

/*
@RunWith(AndroidJUnit4.class)
*/
public class BuyerOrderDetailsActivityTest {
    /*Meal meal = new Meal("Poulet au miel","Un délicieux poulet au miel",
            "Vous ne pourrez pas résister à ce savoureux poulet",R.drawable.poulet);
    Order order = new Order( "1", new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime(),
            OrderStatus.unassigned,"2","3");*/
    /*
    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void cleanup() {
        Intents.release();
    }
    /*
    @Test
    public void TestTransitionToVendorActivity(){
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), BuyerOrderDetailsActivity.class);
        intent.putExtra("orderId",order.getOrderId());
        ActivityScenario<BuyerOrderDetailsActivity> scenario = ActivityScenario.launch(intent);
        onView(withId(R.id.go_vendor_profile_id)).perform(click());
        intended(hasComponent(VendorProfileActivity.class.getName()));
        scenario.close();
    }*/
    /*
    @Test
    public void TestOrderAttributesAreUpdated(){
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), BuyerOrderDetailsActivity.class);
        intent.putExtra("order",order);
        ActivityScenario<BuyerOrderDetailsActivity> scenario = ActivityScenario.launch(intent);
        onView(withId(R.id.go_vendor_profile_id)).check(matches(withText(order.getMealVendor())));
        onView(withId(R.id.mealName)).check(matches(withText(order.getMeal().getName())));
        onView(withId(R.id.mealDesc)).check(matches(withText(order.getMeal().getShortDescription())));
        scenario.close();
    }*/
}
