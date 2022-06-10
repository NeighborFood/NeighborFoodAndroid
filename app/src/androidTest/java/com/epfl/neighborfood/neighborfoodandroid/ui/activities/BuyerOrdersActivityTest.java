package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

/*
@RunWith(AndroidJUnit4.class)
*/
public class BuyerOrdersActivityTest {
    /*
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

    @Test
    public void testTransitionToOrderDetailsActivity(){
        Meal meal = new Meal("Poulet au miel","Un délicieux poulet au miel",
                "Vous ne pourrez pas résister à ce savoureux poulet","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon,new ArrayList<>(),0,new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime());
        Order order = new Order( meal, new GregorianCalendar(2022, Calendar.FEBRUARY, 1).getTime(),
                false,"Vendor A");
        onData(anything()).inAdapterView(withId(R.id.order_list_view)).atPosition(0).
                perform(click());
        intended(hasComponent(BuyerOrderDetailsActivity.class.getName()));
        Order intentOrder = (Order) getIntents().get(0).getSerializableExtra("order");
        assertEquals(order.getOrderId(),intentOrder.getBuyerId());
    }*/
}
