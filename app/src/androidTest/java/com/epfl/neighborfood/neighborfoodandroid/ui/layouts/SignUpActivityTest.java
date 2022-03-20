package com.epfl.neighborfood.neighborfoodandroid.ui.layouts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.login.LoggedInUser;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    private LoggedInUser fakeLoggedInUser = new LoggedInUser("FakeName","FakeEmail@gmail.com");
    @Rule
    public ActivityScenarioRule<SignUpActivity> testRule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Before
    public void logOut(){
        testRule.getScenario().onActivity(activity -> {
            activity.signOut();
                });

    }

    @Test
    public void logInButtonIsVisibleWhenLoggedOut(){
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void logOutButtonIsInvisibleWhenLoggedOut(){
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void startButtonIsInvisibleWhenLoggedOut(){
        onView(withId(R.id.start_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void guideTextWithRightStringWhenLoggedOut(){
        onView(withId(R.id.guide_textView)).check(matches(withText("Please connect via your google account!")));
    }

    @Test
    public void intentFiredWhenSignInButtonClicked(){
        Intents.init();
        onView(withId(R.id.sign_in_button)).perform(click());
        intended(toPackage("com.epfl.neighborfood.neighborfoodandroid"));
        Intents.release();
    }

    @Test
    public void welcomeMessageIsDisplayed(){
        onView(withId(R.id.welcome_text)).check(matches(isDisplayed()));
    }

    @Test
    public void logoImageIsDisplayed(){
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    public void updateUIWithFakeLoggedInUser(){
        testRule.getScenario().onActivity(activity -> {
            activity.updateUI(fakeLoggedInUser);
        });
    }

    @Test
    public void logInButtonIsInvisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void logOutButtonIsVisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void startButtonIsVisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.start_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void guideTextContainGreetingMessageWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.guide_textView)).check(matches(withText("Welcome: "+ fakeLoggedInUser.toString()+". Click start to discover the daily meals")));
    }


    /*@Test
    public void startButtonFiresIntentWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        Intents.init();
        onView(withId(R.id.start_button)).perform(click());
        intended(toPackage("com.epfl.neighborfood.neighborfoodandroid"));
        Intents.release();
    }*/

    @Test
    public void logOutButtonResetsActivityToSignInState(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.sign_out_button)).perform(click());
        onView(withId(R.id.guide_textView)).check(matches(withText("Please connect via your google account!")));
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.start_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }



}
