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
import com.epfl.neighborfood.neighborfoodandroid.database.CollectionSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ChatRoomActivity;
import com.google.android.gms.tasks.Continuation;

import java.util.ArrayList;
import java.util.Comparator;
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
                    c.setId(d.getId());
                    for (User u : c.getUsers()){
                        if (u.getId().equals(AuthenticatorFactory.getDependency().getCurrentUser().getId())){
                            aux.add(c);
                        }
                    }
                }
            }
            return aux;
        }).addOnSuccessListener(l-> {
            l.sort(new Comparator<Conversation>() {
                @Override
                public int compare(Conversation a, Conversation b) {
                    return b.lastMessage().getDate().compareTo(a.lastMessage().getDate());
                }
            });
            adapter.clear();
            adapter.addAll(l);

        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(view.getContext(), ChatRoomActivity.class);
                i.putExtra("Chatter",conversations.get(position).chatter());
                i.putExtra("ConversationID",conversations.get(position).id());
                startActivity(i);
            }
        });
    }
}
