package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;

@RunWith(AndroidJUnit4.class)
public class ChatRoomActivityTest {
    private final User authenticadted = new User("0","me@epfl.ch","me", "notyou","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon);
    private final User other = new User("1","other@epfl.ch","George", "Other","android.resource://com.neighborfood.neighborfoodandroid/" + R.drawable.icon);

    @Before
    public void setUp() throws Exception {
        NeighborFoodApplication.appContainer = new AppContainerTestImplementation();
        AuthenticatorFactory.setDependency(DummyAuthenticator.getInstance());
        DummyAuthenticator.getInstance().logInWithGoogleAccount(null);
        //DummyAuthenticator.getInstance().setAuthUser(new AuthenticatorUser(authenticadted.getId(), authenticadted.getEmail(),authenticadted.getFirstName(),authenticadted.getLastName(),authenticadted.getProfilePictureURI()) {
       // });
        DummyDatabase.getInstance().reset();
        Intents.init();
    }

    @After
    public void cleanup() {
        DummyDatabase.getInstance().reset();
        Intents.release();
    }

    @Test
    public void sendNewMessageTest() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ChatRoomActivity.class);
        intent.putExtra("Chatter",other);
        ActivityScenario<ChatRoomActivity> scenario = ActivityScenario.launch(intent);
        String message = "Thank You";
        onView(ViewMatchers.withId(R.id.edit_gchat_message)).perform(typeText(message));
        //onView(withId(R.id.button_gchat_send)).perform(click());
        //onView(withText(message)).check(matches(isDisplayed()));
        scenario.close();
    }


}
