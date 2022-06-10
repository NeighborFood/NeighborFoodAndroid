package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatRoomViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MutableLiveData<Conversation> conversationLiveData;

    public ChatRoomViewModel(AuthRepository authRepository, UserRepository userRepository, ConversationRepository conversationRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        conversationLiveData = new MutableLiveData<>();
    }

    /**
     * fetches the user that is chatting with the authenticated user in a certain conversation
     *
     * @param conversation conversation between the two users
     * @return a task containing the User fetched
     */
    public Task<User> getChatter(Conversation conversation) {
        return userRepository.getUserById(conversation.chatter(authRepository.getAuthUser().getId()));
    }

    /**
     * adds a new message to the conversation
     *
     * @param conversation conversation holding the new message
     * @param message      the new message to be added to the conversation
     * @return task of the message sending
     */
    public Task<Void> sendMessage(Conversation conversation, String message) {
        conversation.addMessage(new Message(message, authRepository.getAuthUser().getId()));
        return conversationRepository.updateConversation(conversation, conversation.getId());
    }

    /**
     * gets the live data of a conversation
     *
     * @param convoID conversation id
     * @return live data of the conversation
     */
    public LiveData<Conversation> getConversationLiveData(String convoID) {
        conversationRepository.getConversation(convoID).continueWithTask(
                t -> {
                    if (t.getResult() == null) {
                        List<String> usersList = new ArrayList<>(Arrays.asList(convoID.split("-")));
                        return conversationRepository.addConversation(convoID, new Conversation(convoID, usersList, new ArrayList<>()));
                    }
                    return Tasks.forResult(t.getResult());
                }
        ).addOnSuccessListener(conversationLiveData::postValue);
        conversationRepository.addOnConversationChangeListener(convoID, newModel -> {
            conversationLiveData.postValue(newModel.toModel(Conversation.class));
        });
        return conversationLiveData;
    }

    /**
     * fetches user by id
     *
     * @param id id of user
     * @return a task containing the User fetched
     */
    public Task<User> getUserById(String id) {
        return userRepository.getUserById(id);
    }
}
