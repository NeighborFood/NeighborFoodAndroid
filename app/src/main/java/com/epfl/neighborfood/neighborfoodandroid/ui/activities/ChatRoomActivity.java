package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MessageListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Continuation;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity{


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private User chatter;
    private Conversation conv;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        messageList = new ArrayList<>();


        Intent i = getIntent();
        chatter = (User)i.getSerializableExtra("Chatter");
        id = (String) i.getSerializableExtra("ConversationID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //back arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_gchannel);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(chatter.fullName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText(toolbar.getTitle());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messageList);

        DatabaseFactory.getDependency().fetch("Conversations",id).continueWith(
                t -> {
                    Conversation c = t.getResult().toModel(Conversation.class);
                    return c;
                }
        ).addOnSuccessListener(
                c -> {
                    messageList.addAll(c.getMessages());
                    mMessageAdapter.notifyDataSetChanged();
                }
        );

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        Button send = (Button) findViewById(R.id.button_gchat_send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText message = (EditText) findViewById(R.id.edit_gchat_message);
                String messageText = message.getText().toString();

                User currentUser = AuthenticatorFactory.getDependency().getCurrentUser();
                Message msg = new Message(messageText,currentUser,chatter);
                mMessageAdapter.addMessage(msg);

                DatabaseFactory.getDependency().fetch("Conversations",id).continueWith((Continuation<DocumentSnapshot,Conversation>) task ->{
                    Conversation aux = null;
                    if (task.isSuccessful()){
                        aux = task.getResult().toModel(Conversation.class);
                    }
                    return aux;
                }).continueWith(
                        t ->{
                            Conversation c = t.getResult();
                            c.addMessage(msg);
                            DatabaseFactory.getDependency().set("Conversations",id,c);
                            return null;

                        }
                );
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