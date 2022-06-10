package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.epfl.neighborfood.neighborfoodandroid.AppContainerTestImplementation;
import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;

import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ConversationsActivityTest {


    @Before
    public void setUp() throws Exception {
        NeighborFoodApplication.appContainer = new AppContainerTestImplementation();
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
/*
        DummyDatabase db = DummyDatabase.getInstance();
        User[] users = {
                new User("1", "test1@machin.com", "Test", "One",""),
                new User("2", "test2@machin.com", "Test", "Two",""),
                new User("3", "test3@machin.com", "Test", "Three","")
        };

        User currentUser = ((DummyAuthenticator)(AuthenticatorFactory.getDependency())).getCurrentUser();
        Message[] messages = {
                new Message("All good !",
                        users[0].getId()),

                new Message("Where are You ? ",users[1].getId()),

                new Message("Thanks! very nice Meal", users[2].getId())};

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ConversationsActivity.class);
        ActivityScenario<ConversationsActivity> scenario = ActivityScenario.launch(intent);

        DummyDatabase.getInstance().reset();

        for (int i = 0; i < users.length; i++) {
            List<String> aux = new ArrayList<>();
            aux.add(users[i].getId());
            aux.add(((DummyAuthenticator)(AuthenticatorFactory.getDependency())).getCurrentUser().getId());
            Conversation conv = new Conversation("1-2-3",aux, Arrays.asList(messages[i]));
            db.pushConversation(conv);
        }


        onView(ViewMatchers.withId(R.id.conversationsListView)).check(matches(isDisplayed()));

        onData(instanceOf(Conversation.class))
                .inAdapterView(allOf(withId(R.id.conversationsListView), isDisplayed()))
                .atPosition(db.fetchConversations().size() - 1)
                .check(matches(isDisplayed()));


        for (int i = 0; i < users.length; i++) {
            User usr = users[i];
            onData(anything()).inAdapterView(withId(R.id.conversationsListView)).atPosition(i).
                    onChildView(withId(R.id.user_name)).
                    check(matches(withText(usr.getUsername())));
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
        scenario.close();*/
    }


    @Test
    public void conversationsClickableTest() {
/*
        DummyDatabase db = DummyDatabase.getInstance();
        User[] users = {
                new User("1", "test1@machin.com", "Test", "One",""),
                new User("2", "test2@machin.com", "Test", "Two",""),
        };

        User currentUser = ((DummyAuthenticator)(AuthenticatorFactory.getDependency())).getCurrentUser();
        Message[] messages = {
                new Message("All good !", currentUser,
                        new User("1", "test1@machin.com", "Test", "One","")),

                new Message("Where are You ? ", new User("2", "test2@machin.com", "Test", "Two",""),
                        currentUser),

                new Message("Thanks! very nice Meal", new User("3", "test3@machin.com", "Test", "Three",""),
                        currentUser)};


        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), ConversationsActivity.class);
        ActivityScenario<ConversationsActivity> scenario = ActivityScenario.launch(intent);

        DummyDatabase.getInstance().reset();


        for (int i = 0; i < users.length; i++) {
            List<User> aux = new ArrayList<>();
            aux.add(users[i]);
            aux.add(((DummyAuthenticator)(AuthenticatorFactory.getDependency())).getCurrentUser());
            Conversation conv = new Conversation(aux, Arrays.asList(messages[i]));
            db.pushConversation(conv);
        }



        onData(anything()).inAdapterView(withId(R.id.conversationsListView)).atPosition(0).
                perform(click());
        intended(hasComponent(ChatRoomActivity.class.getName()));
        intended(hasExtra("Chatter", users[0]));
        Espresso.pressBack();

        scenario.close();
    */}
}
