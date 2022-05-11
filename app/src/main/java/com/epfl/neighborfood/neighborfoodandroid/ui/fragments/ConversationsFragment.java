package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.ConversationListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepositoryImplementation;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ChatRoomActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationsFragment extends Fragment {
    public static final int IMGID = R.drawable.profile_img_male;
    private ListView listView;
    private ConversationListAdapter adapter;
    private List<Conversation> conversations = new ArrayList<>();


    public ConversationsFragment(){
        super(R.layout.fragment_conversations);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //ConversationRepository rep = new ConversationRepositoryImplementation();

        listView = (ListView) view.findViewById(R.id.conversationsFragmentListView);
        adapter = new ConversationListAdapter(view.getContext(), (ArrayList<Conversation>) conversations);
        listView.setAdapter(adapter);

        DatabaseFactory.getDependency().fetchAll("Conversations").continueWith((Continuation<CollectionSnapshot,List<Conversation>>) task ->{
            List<Conversation> aux = new ArrayList<>();
            if (task.isSuccessful()){
                for (DocumentSnapshot d : task.getResult().getDocuments()){
                    Conversation c = d.toModel(Conversation.class);
                    for (User u : c.getUsers()){
                        if (u.getId().equals("-1")){
                            aux.add(c);
                        }
                    }
                }
            }
            return aux;
        }).addOnSuccessListener(l-> {
                conversations = l;
                Toast.makeText(getContext(),"I am here",Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
        });



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
