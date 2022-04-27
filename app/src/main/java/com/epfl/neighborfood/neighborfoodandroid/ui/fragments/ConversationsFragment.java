package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.ConversationListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ChatRoomActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationsFragment extends Fragment {
    public static final int IMGID = R.drawable.profile_img_male;
    private ListView listView;
    private ConversationListAdapter adapter;
    private ArrayList<Conversation> conversations = new ArrayList<>();
    public ConversationsFragment(){
        super(R.layout.fragment_conversations);
    }
    /*
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return null }*/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        DummyDatabase dep = DummyDatabase.getInstance();
        User[] users = {
                new User("1", "test1@machin.com", "Test", "One"),
                new User("2", "test2@machin.com", "Test", "Two"),
                new User("3", "test3@machin.com", "Test", "Three")
        };

        User currentUser = AuthenticatorFactory.getDependency().getCurrentUser();
        Message[] messages = {
                new Message("All good !", currentUser,
                        new User("1", "test1@machin.com", "Test", "One")),

                new Message("Where are You ? ", new User("2", "test2@machin.com", "Test", "Two"),
                        currentUser),

                new Message("Thanks! very nice Meal", new User("3", "test3@machin.com", "Test", "Three"),
                        currentUser)};
        DummyDatabase.getInstance().reset();

        for (int i = 0; i < users.length; i++) {
            List<User> aux = new ArrayList<>();
            aux.add(users[i]);
            aux.add(AuthenticatorFactory.getDependency().getCurrentUser());
            Conversation conv = new Conversation(aux, Arrays.asList(messages[i]));
            dep.pushConversation(conv);
            DatabaseFactory.getDependency().add("/test",conv);
        }
        conversations = dep.fetchConversations();



        listView = (ListView) view.findViewById(R.id.conversationsFragmentListView);
        adapter = new ConversationListAdapter(view.getContext(), conversations);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(view.getContext(), ChatRoomActivity.class);
                i.putExtra("Chatter",conversations.get(position).chatter());
                startActivity(i);
            }
        });
    }
}
