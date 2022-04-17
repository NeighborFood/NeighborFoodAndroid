package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.epfl.neighborfood.neighborfoodandroid.util.matchers.ImageHasDrawableMatcher.hasDrawable;
import static com.epfl.neighborfood.neighborfoodandroid.util.matchers.NthChildOfMatcher.nthChildOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
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
import com.google.android.gms.auth.api.Auth;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;


@RunWith(AndroidJUnit4.class)

public class ProfileEditingActivityTest {
    public static final String KEY_IMAGE_DATA = "data";
    private AuthRepositoryTestImplementation authRepo;
    private User dummyUser = new UserTestImplementation("-1","zbiba@epfl.ch","Zbiba","Zabboub");
    @Rule
    public ActivityScenarioRule<ProfileEditingActivity> testRule = new ActivityScenarioRule<>(ProfileEditingActivity.class);

    @BeforeClass
    public static void setupApp(){
        NeighborFoodApplication.appContainer = new AppContainerTestImplementation();
    }

    @Before
    public void setUp() throws Exception {


        Intents.init();
        NeighborFoodApplication app = ApplicationProvider.getApplicationContext();
        authRepo = (AuthRepositoryTestImplementation) app.getAppContainer().getAuthRepo();
        authRepo.logOut();
        //Espresso.closeSoftKeyboard();
    }

    @Test
    public void buttonSaveSavesBioTest(){
        String testBio = "Test Bio";
        authRepo.setUser(dummyUser);
        onView(withId(R.id.bioValue)).perform(clearText(),click(),typeText(testBio),closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(scrollTo(),click());
        assertThat(authRepo.getUserLiveData().getValue().getBio(),is(testBio) );
        //assertTrue(testRule.getScenario().getState() == Lifecycle.State.DESTROYED);
    }
    @Test
    public void backFinishesActivity(){
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertThat(testRule.getScenario().getResult().getResultCode(),is(Activity.RESULT_CANCELED));
    }
    @Test
    public void buttonAddLinkAddsLink(){
        dummyUser.setLinks(new ArrayList<>());
        authRepo.setUser(dummyUser);
        onView(withId(R.id.profileEditLinksLayout)).check(matches(hasChildCount(1)));
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditLinksLayout)).check(matches(hasChildCount(2)));

    }
    @Test
    public void linksFieldsContainUserLinksPlusEmpty(){
        ArrayList<String> fakeLinks = new ArrayList<>();
        fakeLinks.add("a");fakeLinks.add("b");fakeLinks.add("c");
        dummyUser.setLinks(fakeLinks);
        authRepo.setUser(dummyUser);
        //verify number of children corresponds to what expected
        onView(withId(R.id.profileEditLinksLayout)).check(matches(hasChildCount(fakeLinks.size()+1)));
        //Verify text fiel contains actual links
        for(int i = 0; i < fakeLinks.size();++i){
            onView(nthChildOf(withId(R.id.profileEditLinksLayout),i)).check(matches(withText(fakeLinks.get(i))));
        }
        onView(nthChildOf(withId(R.id.profileEditLinksLayout),fakeLinks.size())).check(matches(withText("")));
    }
    @Test
    public void multipleEmptyLinksDontGetSavedwithUser(){
        ArrayList<String> fakeLinks = new ArrayList<>();
        fakeLinks.add("a");fakeLinks.add("b");fakeLinks.add("c");
        dummyUser.setLinks(fakeLinks);
        authRepo.setUser(dummyUser);authRepo.setUser(dummyUser);
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.saveButton)).perform(scrollTo(),click());
        assertThat(authRepo.getUserLiveData().getValue().getLinks(),is(fakeLinks));

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
        authRepo.setUser(dummyUser);
        onView(withId(R.id.nameValue)).check(matches(withText(dummyUser.getFirstName())));
        onView(withId(R.id.surnameValue)).check(matches(withText(dummyUser.getLastName())));
        onView(withId(R.id.emailValue)).check(matches(withText(dummyUser.getEmail())));

    }
    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}