package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MessageListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.database.Database;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity{


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private User chatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Database dep = DatabaseFactory.getDependency();
        messageList = new ArrayList<>();


        Intent i = getIntent();
        chatter = (User)i.getSerializableExtra("Chatter");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //back arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_gchannel);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(chatter.getUsername());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText(toolbar.getTitle());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
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


                User currentUser = ((NeighborFoodApplication)getApplication()).getAppContainer().getAuthRepo().getCurrentUser();
                Message msg = new Message(messageText,currentUser,chatter);
                mMessageAdapter.addMessage(msg);
                message.setText("");
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