package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MessageListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.ChatMessageModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {
    private static final List<ChatMessageModel> dummyList = Lists.newArrayList(
            new ChatMessageModel("Hello Friend !", "Mr Robot"),
            new ChatMessageModel("Who are you  ?", "Elliot"),
            new ChatMessageModel("You!", "Mr Robot"));
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<ChatMessageModel> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);



        //back arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_gchannel);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);

        //mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageAdapter = new MessageListAdapter(this, dummyList);

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        Button send = (Button) findViewById(R.id.button_gchat_send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText message = (EditText) findViewById(R.id.edit_gchat_message);
                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                //this is commented for demo purposes
                /*
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new ChatMessageModel(message.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );
                message.setText("");
                 */

                //this is dummy code
                dummyList.add(new ChatMessageModel(message.getText().toString(),"Elliot"));
                message.setText("");
                dummyList.add(new ChatMessageModel("Take down Ecorp","Mr Robot"));
            }
        });
    }
}