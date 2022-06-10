package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.MessageListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ChatRoomViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private Conversation conv;
    private String id;
    private ChatRoomViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        messageList = new ArrayList<>();
        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) (getApplication()))).get(ChatRoomViewModel.class);

        Intent i = getIntent();
        id = (String) i.getSerializableExtra("ConversationID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //back arrow
        Toolbar toolbar = findViewById(R.id.toolbar_gchannel);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar and remove title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        mMessageRecycler = findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messageList, viewModel);

        viewModel.getConversationLiveData(id).observe(this,
                c -> {
                    if (c == null) {
                        return;
                    }
                    viewModel.getChatter(c).addOnSuccessListener(chatter -> {
                        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
                        getSupportActionBar().setTitle(chatter.getUsername());
                        mTitle.setText(chatter.getUsername());
                    });
                    conv = c;
                    messageList.clear();
                    messageList.addAll(c.getMessages());
                    mMessageAdapter.notifyDataSetChanged();
                    if (c.getMessages().size() > 0) {
                        ((RecyclerView) findViewById(R.id.recycler_gchat)).smoothScrollToPosition(c.getMessages().size() - 1);
                    }
                }
        );

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        Button send = findViewById(R.id.button_gchat_send);


        send.setOnClickListener(v -> {
            EditText message = findViewById(R.id.edit_gchat_message);
            String messageText = message.getText().toString();
            message.setText("");
            viewModel.sendMessage(conv, messageText);
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
