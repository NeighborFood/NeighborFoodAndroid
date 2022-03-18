package com.epfl.neighborfood.neighborfoodandroid;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;

import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ChatRoomActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ConversationsActivity;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;


import android.content.Intent;


import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class ConversationsActivityTest {


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
    public void conversationsAppearTest() {

        DummyDatabase db = DummyDatabase.getInstance();
        User[] users = {
                new User(1, "test1@machin.com", "Test", "One"),
                new User(2, "test2@machin.com", "Test", "Two"),
                new User(3, "test3@machin.com", "Test", "Three")
        };

        User currentUser = AuthenticatorFactory.getDependency().getCurrentUser();
        Message[] messages = {
                new Message("All good !", currentUser,
                        new User(1, "test1@machin.com", "Test", "One")),

                new Message("Where are You ? ", new User(2, "test2@machin.com", "Test", "Two"),
                        currentUser),

                new Message("Thanks! very nice Meal", new User(3, "test3@machin.com", "Test", "Three"),
                        currentUser)};

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ConversationsActivity.class);
        ActivityScenario<ConversationsActivity> scenario = ActivityScenario.launch(intent);

        DummyDatabase.getInstance().reset();

        for (int i = 0; i < users.length; i++) {
            Conversation conv = new Conversation(users[i], Arrays.asList(messages[i]));
            db.pushConversation(conv);
        }


        onView(withId(R.id.conversationsListView)).check(matches(isDisplayed()));

        onData(instanceOf(Conversation.class))
                .inAdapterView(allOf(withId(R.id.conversationsListView), isDisplayed()))
                .atPosition(db.fetchConversations().size() - 1)
                .check(matches(isDisplayed()));


        for (int i = 0; i < users.length; i++) {
            User usr = users[i];
            onData(anything()).inAdapterView(withId(R.id.conversationsListView)).atPosition(i).
                    onChildView(withId(R.id.user_name)).
                    check(matches(withText(usr.getFullName())));
        }


        for (int i = 0; i < messages.length; i++) {
            Message msg = messages[i];
            String txt = msg.getContent();
            if (msg.getSender().getId() == currentUser.getId()) {
                txt = "You : " + txt;
            }
            onData(anything()).inAdapterView(withId(R.id.conversationsListView)).atPosition(i).
                    onChildView(withId(R.id.user_last_message)).
                    check(matches(withText(txt)));

        }
        scenario.close();
    }


    @Test
    public void conversationsClickableTest() {

        DummyDatabase db = DummyDatabase.getInstance();
        User[] users = {
                new User(1, "test1@machin.com", "Test", "One"),
                new User(2, "test2@machin.com", "Test", "Two"),
        };

        User currentUser = AuthenticatorFactory.getDependency().getCurrentUser();
        Message[] messages = {
                new Message("All good !", currentUser,
                        new User(1, "test1@machin.com", "Test", "One")),

                new Message("Where are You ? ", new User(2, "test2@machin.com", "Test", "Two"),
                        currentUser),

                new Message("Thanks! very nice Meal", new User(3, "test3@machin.com", "Test", "Three"),
                        currentUser)};


        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ConversationsActivity.class);
        ActivityScenario<ConversationsActivity> scenario = ActivityScenario.launch(intent);

        DummyDatabase.getInstance().reset();


        for (int i = 0; i < users.length; i++) {
            Conversation conv = new Conversation(users[i], Arrays.asList(messages[i]));
            db.pushConversation(conv);
        }



        onData(anything()).inAdapterView(withId(R.id.conversationsListView)).atPosition(0).
                perform(click());
        intended(hasComponent(ChatRoomActivity.class.getName()));
        intended(hasExtra("Chatter", users[0]));
        Espresso.pressBack();

        scenario.close();
    }
}
