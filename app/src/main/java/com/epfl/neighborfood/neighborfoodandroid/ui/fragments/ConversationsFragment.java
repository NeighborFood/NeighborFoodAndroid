package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.ConversationListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ChatRoomActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ConversationsViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversationsFragment extends Fragment {
    public static final int IMGID = R.drawable.profile_img_male;
    private ListView listView;
    private ConversationListAdapter adapter;
    private List<Pair<Conversation,User>> conversations = new ArrayList<>();
    private ConversationsViewModel viewModel;


    public ConversationsFragment(){
        super(R.layout.fragment_conversations);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){



        viewModel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) (getActivity().getApplication()))).get(ConversationsViewModel.class);
        listView = view.findViewById(R.id.conversationsFragmentListView);
        adapter = new ConversationListAdapter(view.getContext(), (ArrayList<Pair<Conversation, User>>) conversations,viewModel);
        listView.setAdapter(adapter);
        viewModel.fetchAllCurrentUserConversations().addOnSuccessListener(l-> {
            Collections.sort(l, (a, b) -> b.getFirst().lastMessage().getDate().compareTo(a.getFirst().lastMessage().getDate()));
            adapter.clear();
            adapter.addAll(l);
            conversations = l;

        });



        listView.setOnItemClickListener((parent, v, position, id) -> {
            Intent i = new Intent(view.getContext(), ChatRoomActivity.class);
            i.putExtra("ConversationID",conversations.get(position).getFirst().getId());
            startActivity(i);
        });
    }
}
