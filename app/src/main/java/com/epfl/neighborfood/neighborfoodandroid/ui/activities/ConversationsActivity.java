package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.ConversationListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import java.util.ArrayList;
import java.util.Arrays;


public class ConversationsActivity extends AppCompatActivity {

    public static final int IMGID = R.drawable.profile_img_male;
    private ListView view;
    private ConversationListAdapter adapter;
    private ArrayList<Conversation> conversations = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        DummyDatabase dep = DummyDatabase.getInstance();
        dep.pushConversation(
                new Conversation( new User(1,"test1@machin.com","Test","One"),
                        Arrays.asList(new Message("Hello",new User(1,"test1@machin.com","Test","One"),
                                AuthenticatorFactory.getDependency().getCurrentUser())))
        );
        dep.pushConversation(
                new Conversation( new User(2,"test2@machin.com","Test","Two"),
                        Arrays.asList(new Message("Hello",AuthenticatorFactory.getDependency().getCurrentUser(),new User(2,"test2@machin.com","Test","Two")
                                )))
        );
        conversations = dep.fetchConversations();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_conversations);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Conversations");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText(toolbar.getTitle());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        view = (ListView) findViewById(R.id.conversationsListView);
        adapter = new ConversationListAdapter(this, conversations);
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ConversationsActivity.this, ChatRoomActivity.class);
                i.putExtra("Chatter",conversations.get(position).getChatter());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}