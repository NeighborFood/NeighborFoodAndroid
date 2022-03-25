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
import com.epfl.neighborfood.neighborfoodandroid.login.googleLogin.GoogleAccount;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    // a fake LoggedInUser that will be used to simulate the case where an actual account is logged in into the app
    private LoggedInUser fakeLoggedInUser = new LoggedInUser("FakeName","FakeEmail@gmail.com");

    @Rule
    public ActivityScenarioRule<SignUpActivity> testRule = new ActivityScenarioRule<>(SignUpActivity.class);

    /**
     * Makes sure that we are logged out before the start of every test
     */
    @Before
    public void logOutAndSetUp(){
        testRule.getScenario().onActivity(activity -> {
            activity.signOut();
                });
        Intents.init();
    }

    @After
    public void cleanUp(){
        Intents.release();
    }


    /**
     * Checks if the button responsible to log in is visible when no user is logged in
     */
    @Test
    public void logInButtonIsVisibleWhenLoggedOut(){
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Checks if the button responsible to log out is invisible when no user is logged in
     */
    @Test
    public void logOutButtonIsInvisibleWhenLoggedOut(){
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    /**
     * Checks if the button responsible to start seeing the meals is invisible when no user is logged in
     */
    @Test
    public void startButtonIsInvisibleWhenLoggedOut(){
        onView(withId(R.id.start_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
    /**
     * Checks that the guiding message is showing the right indication when no user is logged in
     */
    @Test
    public void guideTextWithRightStringWhenLoggedOut(){
        onView(withId(R.id.guide_textView)).check(matches(withText("Please connect via your google account!")));
    }

    /**
     * Checks if a sign in intent is fired when the sign in button is used
     */
    @Test
    public void intentFiredWhenSignInButtonClicked(){
        onView(withId(R.id.sign_in_button)).perform(click());
        intended(toPackage("com.epfl.neighborfood.neighborfoodandroid"));
    }

    /**
     * checks if the welcome message is always displayed
     */
    @Test
    public void welcomeMessageIsDisplayed(){
        onView(withId(R.id.welcome_text)).check(matches(isDisplayed()));
    }

    /**
     * checks if the logo image is always displayed
     */
    @Test
    public void logoImageIsDisplayed(){
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    /**
     * this function will be used to simulate that an actual user is logged in into the app
     */
    public void updateUIWithFakeLoggedInUser(){
        testRule.getScenario().onActivity(activity -> {
            activity.updateUI(fakeLoggedInUser);
        });
    }

    /**
     * Checks if the button responsible to log in is invisible when a user is logged in
     */
    @Test
    public void logInButtonIsInvisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.sign_in_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    /**
     * Checks if the button responsible to log out is visible when a user is logged in
     */
    @Test
    public void logOutButtonIsVisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.sign_out_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Checks if the button responsible to start seeing the meals is visible when a user is logged in
     */
    @Test
    public void startButtonIsVisibleWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.start_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Checks if the guiding message is showing the right indication when a user is logged in
     */
    @Test
    public void guideTextContainGreetingMessageWhenLoggedIn(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.guide_textView)).check(matches(withText("Welcome: "+ fakeLoggedInUser.toString()+". Click start to discover the daily meals")));
    }


    /**
     * Checks if a sign in intent is fired when the sign in button is used
     */
    /*
    @Test
    public void intentFiredWhenStartClicked(){
        updateUIWithFakeLoggedInUser();
        onView(withId(R.id.start_button)).perform(click());
        intended(toPackage("com.epfl.neighborfood.neighborfoodandroid"));
    }*/

    /**
     * Checks finalizeLogin WorksWithDefaultCred
     */
    @Test
    public void finalizeLoginTest(){
        testRule.getScenario().onActivity(activity -> {
            AuthCredential authCredential = GoogleAuthProvider.getCredential("2022", null);
            GoogleAccount googleAccount = new GoogleAccount(GoogleSignInAccount.createDefault());
            activity.finalizeLoginWithFirebase(authCredential);
        });
    }


    /**
     * Checks that when the log out button is pressed (when a user is logged in) we go back to the state of no user is logged in
     * It is done by rechecking the visibility of different component
     */
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
