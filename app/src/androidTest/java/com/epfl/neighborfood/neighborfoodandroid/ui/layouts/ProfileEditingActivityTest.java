package com.epfl.neighborfood.neighborfoodandroid.ui.layouts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.epfl.neighborfood.neighborfoodandroid.util.matchers.ImageHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;


import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.models.UserTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.MainActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ProfileEditingActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import javax.inject.Inject;


//@RunWith(AndroidJUnit4.class)

public class ProfileEditingActivityTest {
    public static final String KEY_IMAGE_DATA = "data";
    private AuthRepository authRepo;
    @Rule
    public ActivityScenarioRule<ProfileEditingActivity> testRule = new ActivityScenarioRule<>(ProfileEditingActivity.class);

    @Before
    public void setUp() throws Exception {

        Intents.init();
        NeighborFoodApplication app = ApplicationProvider.getApplicationContext();
        authRepo = app.getAppContainer().getAuthRepo();
        authRepo.logOut();
    }

    @Test
    public void buttonSaveTest(){

        onView(withId(R.id.saveButton)).perform(click());
        //assertTrue(testRule.getScenario().getState() == Lifecycle.State.DESTROYED);

    }

    //Clicking on the image shows the image picker
    @Test
    public void startImagePickerTest(){
        onView(withId(R.id.profilePictureImageView)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_PICK),hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)));

    }

    //Selecting an image displays the correct image
    @Test
    public void imagePickerShowsChosenImageTest(){
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
        intending(hasAction(Intent.ACTION_PICK)).respondWith(createImagePickerResultStub());
        onView(withId(R.id.profilePictureImageView)).perform(click());
        onView(withId(R.id.profilePictureImageView)).check(matches(hasDrawable()));

    }

    @Test
    public void imagePickerDoesNothingOnNullData(){
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
        intending(hasAction(Intent.ACTION_PICK)).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,null));
        onView(withId(R.id.profilePictureImageView)).perform(click());
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
    }
    @Test
    public void imagePickerDoesNothingOnNullExtra(){
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
        intending(hasAction(Intent.ACTION_PICK)).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,new Intent()));
        onView(withId(R.id.profilePictureImageView)).perform(click());
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
    }
    private Instrumentation.ActivityResult createImagePickerResultStub(){
        Bundle bundle = new Bundle();
        Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
        bundle.putParcelable(ProfileEditingActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(res,R.drawable.ic_launcher_background));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK,new Intent().putExtras(bundle));
    }

    @Test
    public void uiReflectsUser(){
        User c = new UserTestImplementation("-1","zbiba@epfl.ch","Zbiba","Zabboub");
        testRule.getScenario().onActivity(activity -> {
            activity.updateUserFields(c);
        });
        onView(withId(R.id.nameValue)).check(matches(withText(c.getFirstName())));
        onView(withId(R.id.surnameValue)).check(matches(withText(c.getLastName())));
        onView(withId(R.id.emailValue)).check(matches(withText(c.getEmail())));

    }
    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}