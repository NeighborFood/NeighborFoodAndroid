package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MessageListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private User chatter = new User(1,"ww@epfl.ch","Walter", "White");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //how are we gonna edit this
        DummyDatabase dep = DummyDatabase.getInstance();
        messageList = new ArrayList<>(dep.fetchMessages());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //chatter = (User) getIntent().getSerializableExtra("MyClass");

        //back arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_gchannel);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(chatter.getFullName());
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messageList);

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
                dep.pushMessage(msg);
                mMessageAdapter.addMessage(msg);
                message.setText("");
            }
        });
    }
}