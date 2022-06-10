package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorSingleton;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ChatRoomViewModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Adapter class for messages list in a conversation
 */
public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final String DATE_FORMAT = "d MMM yyyy, HH:mm";
    private final ChatRoomViewModel viewModel;

    private final List<Message> mMessageList;

    /**
     * @param context
     * @param messageList list of messages
     * @param viewModel   viewModel for the chatroom
     */
    public MessageListAdapter(Context context, List<Message> messageList, ChatRoomViewModel viewModel) {
        mMessageList = messageList;
        this.viewModel = viewModel;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = mMessageList.get(position);

        if (message.getSender().equals(AuthenticatorSingleton.getDependency()
                .getCurrentAuthUser().getId())) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.me_message, parent, false);
            return new SentMessageHolder(view);
        } else  {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.other_message, parent, false);
            return new ReceivedMessageHolder(view);
        }
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }


    private static class SentMessageHolder extends RecyclerView.ViewHolder {
        final TextView messageText;
        final TextView timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_gchat_message_me);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_me);
        }

        void bind(Message message) {
            messageText.setText(message.getContent());

            // Format the stored timestamp into a readable String using method.
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            timeText.setText(dateFormatter.format(message.getDate()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        final TextView messageText;
        final TextView timeText;
        final TextView nameText;
        final ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_gchat_user_other);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            messageText = itemView.findViewById(R.id.text_gchat_message_other);
            profileImage = itemView.findViewById(R.id.image_gchat_profile_other);
        }

        void bind(Message message) {
            messageText.setText(message.getContent());
            // Format the stored timestamp into a readable String
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            timeText.setText(dateFormatter.format(message.getDate()));
            viewModel.getUserById(message.getSender()).addOnSuccessListener(user -> nameText.setText(user.getUsername()));
        }
    }


}
