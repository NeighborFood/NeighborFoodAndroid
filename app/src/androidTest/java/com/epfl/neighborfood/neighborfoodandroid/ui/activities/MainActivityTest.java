package com.epfl.neighborfood.neighborfoodandroid.ui.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorSingleton;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.AccountFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.ConversationsFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MainFragment;
import com.epfl.neighborfood.neighborfoodandroid.ui.fragments.MealListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
/*
    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);
    @BeforeClass
    public static void setupApp(){
        NeighborFoodApplication.appContainer = new AppContainerTestImplementation();
        AuthenticatorSingleton.setDependency(DummyAuthenticator.getInstance());
        NeighborFoodApplication.appContainer.getAuthRepo().logInWithGoogleAccount(null);
    }
    @Before
    public void setUp() throws Exception {
        //DummyDatabase.getInstance().reset();

        Intents.init();
    }@Test
    public void homeButtonLoadsMealList(){
        onView(withId((R.id.navBarHome))).perform(click());
        /*testRule.getScenario().onActivity(activity ->{
            FragmentManager fragManager = activity.getSupportFragmentManager();
                int count = fragManager.getBackStackEntryCount();
                Fragment frag = fragManager.getFragments().get(count>0?count-1:count);
                assert  frag instanceof MainFragment;
        });*/

    }
    @Test
    public void accountButtonLoadsAccountPage(){
        onView(withId((R.id.navBarAccount))).perform(click());
        /*testRule.getScenario().onActivity(activity ->{
            FragmentManager fragManager = activity.getSupportFragmentManager();
            int count = fragManager.getBackStackEntryCount();
            Fragment frag = fragManager.getFragments().get(count>0?count-1:count);
            assert  frag instanceof AccountFragment;
        });*/

    }
    @Test
    public void messagesButtonLoadsConversations(){
        onView(withId((R.id.navBarMessages))).perform(click());
        /*testRule.getScenario().onActivity(activity ->{
            FragmentManager fragManager = activity.getSupportFragmentManager();
            int count = fragManager.getBackStackEntryCount();
            Fragment frag = fragManager.getFragments().get(count>0?count-1:count);
            assert  frag instanceof ConversationsFragment;
        });*/

    }


    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
*/

}
