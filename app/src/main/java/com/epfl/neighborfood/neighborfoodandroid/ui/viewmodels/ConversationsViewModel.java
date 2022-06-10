package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.ConversationRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

public class ConversationsViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public ConversationsViewModel(AuthRepository authRepository, UserRepository userRepository, ConversationRepository conversationRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    /**
     * fetches user by id
     *
     * @param id id of user
     * @return a task containing the User fetched
     */
    public Task<User> getUser(String id) {
        return userRepository.getUserById(id);
    }

    /**
     * fetches the conversations and corresponding Users that the authenticated user participated in
     *
     * @return Task of the list of Pairs containing the conversations and corresponding Users
     */
    public Task<List<Pair<Conversation, User>>> fetchAllCurrentUserConversations() {
        return conversationRepository.getAllConversations(authRepository.getAuthUser().getId()).continueWithTask(
                tc -> {
                    List<Conversation> conversations = tc.getResult();
                    List<Task<Pair<Conversation, User>>> res = new ArrayList<>();
                    for (Conversation c : conversations) {
                        Task<User> fetchUser = userRepository.getUserById(c.chatter(authRepository.getAuthUser().getId()));
                        res.add(fetchUser.continueWith(tu -> Pair.of(c, tu.getResult())));
                    }
                    return Tasks.whenAllSuccess(res);
                }
        );
    }

    /**
     * fetches current authenticated user
     *
     * @return the current User fetched
     */
    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
