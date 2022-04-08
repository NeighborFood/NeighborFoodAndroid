package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.DummyAuthenticator;
import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

@RunWith(AndroidJUnit4.class)
public class ChatRoomActivityTest {

    private User other = new User("1","other@epfl.ch","George", "Other");

    @Before
    public void setUp() throws Exception {
        DummyDatabase.getInstance().reset();
        Intents.init();
    }

    @After
    public void cleanup() {
        DummyDatabase.getInstance().reset();
        Intents.release();
    }

    @Test
    public void chatMessagesAppearTest() {

        User me = DummyAuthenticator.getInstance().getCurrentUser();
        Message m1 = new Message("Hello, it's me !",me,other);
        Message m2 = new Message("Happy Meal !",other,me);

        DummyDatabase.getInstance().pushMessage(m1);
        DummyDatabase.getInstance().pushMessage(m2);
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ChatRoomActivity.class);
        intent.putExtra("Chatter",other);
        ActivityScenario<ChatRoomActivity> scenario = ActivityScenario.launch(intent);
        onView(withText("Hello, it's me !")).check(matches(isDisplayed()));
        onView(withText("Happy Meal !")).check(matches(isDisplayed()));

        scenario.close();
    }

    @Test
    public void sendNewMessageTest() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ChatRoomActivity.class);
        intent.putExtra("Chatter",other);
        ActivityScenario<ChatRoomActivity> scenario = ActivityScenario.launch(intent);
        String message = "Thank You";
        onView(ViewMatchers.withId(R.id.edit_gchat_message)).perform(typeText(message));
        onView(withId(R.id.button_gchat_send)).perform(click());
        onView(withText(message)).check(matches(isDisplayed()));
        scenario.close();
    }


}
