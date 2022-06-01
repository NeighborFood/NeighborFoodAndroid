package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.epfl.neighborfood.neighborfoodandroid.util.matchers.ImageHasDrawableMatcher.hasDrawable;
import static com.epfl.neighborfood.neighborfoodandroid.util.matchers.NthChildOfMatcher.nthChildOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.models.UserTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepositoryTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.util.viewactions.waitUntilTaskFinishedViewAction;
import com.google.android.gms.tasks.Task;

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
        AuthenticatorFactory.setDependency(DummyAuthenticator.getInstance());
        NeighborFoodApplication.appContainer.getAuthRepo().logInWithGoogleAccount(null);
    }

    @Before
    public void setUp() throws Exception {


        Intents.init();
        NeighborFoodApplication app = ApplicationProvider.getApplicationContext();
        authRepo = (AuthRepositoryTestImplementation) app.getAppContainer().getAuthRepo();
        authRepo.logInWithGoogleAccount(null);
        //authRepo.logOut();
        //Espresso.closeSoftKeyboard();
        DummyDatabase.getInstance().reset();
    }

    @Test
    public void backFinishesActivity(){
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertThat(testRule.getScenario().getResult().getResultCode(),is(Activity.RESULT_CANCELED));
    }
    @Test
    public void linksFieldsContainUserLinksPlusEmpty(){
        Task<DocumentSnapshot> t =DatabaseFactory.getDependency().fetch("Users",dummyUser.getId());
        waitUntilTaskFinishedViewAction.waitUntilFinished(t,2000);
        User user = t.getResult().toModel(User.class);
        ArrayList<String> fakeLinks = user.getLinks();
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
        authRepo.setUser(dummyUser);
        DatabaseFactory.getDependency().set("Users",dummyUser.getId(),dummyUser);
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.profileEditAddLinkButton)).perform(scrollTo(),click());
        onView(withId(R.id.saveButton)).perform(scrollTo(),click());
        assertThat(authRepo.getCurrentUser().getLinks(),is(fakeLinks));

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
       // onView(withId(R.id.profilePictureImageView)).check(matches(hasDrawable()));

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
        intending(hasAction(Intent.ACTION_PICK)).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,new Intent().setData(Uri.parse("android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon))));
        onView(withId(R.id.profilePictureImageView)).perform(click());
        onView(withId(R.id.profilePictureImageView)).check(matches(not(hasDrawable())));
    }
    private Instrumentation.ActivityResult createImagePickerResultStub(){
        Bundle bundle = new Bundle();
        Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
        bundle.putParcelable(ProfileEditingActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(res,R.drawable.ic_launcher_background));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK,new Intent().setData(Uri.parse("android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon)).putExtras(bundle));
    }

    @Test
    public void uiReflectsUser() throws InterruptedException {
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