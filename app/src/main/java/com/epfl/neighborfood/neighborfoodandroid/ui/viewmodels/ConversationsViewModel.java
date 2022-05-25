package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class ConversationsViewModel extends ViewModel {
    private AuthRepository authRepository;
    private UserRepository userRepository;
    private ConversationRepository conversationRepository;
    public ConversationsViewModel(AuthRepository authRepository, UserRepository userRepository, ConversationRepository conversationRepository){
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }
    public Task<User> getUser(String id){
        return userRepository.getUserById(id);
    }

    public Task<List<Conversation>> fetchAllCurrentUserConversations(){
        return conversationRepository.getAllConversations(authRepository.getAuthUser().getId());
    }
    public User getCurrentUser(){
        return authRepository.getCurrentUser();
    }
}
