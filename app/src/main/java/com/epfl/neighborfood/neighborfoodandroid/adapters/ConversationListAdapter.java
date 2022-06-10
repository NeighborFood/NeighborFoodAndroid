package com.epfl.neighborfood.neighborfoodandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.authentication.AuthenticatorFactory;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.ConversationsViewModel;
import com.epfl.neighborfood.neighborfoodandroid.util.Pair;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter class for conversations list
 */
public class ConversationListAdapter extends ArrayAdapter {
    private final ConversationsViewModel viewModel;

    /**
     * @param context
     * @param conversationsArrayList list of conversations
     * @param viewModel              viewModel of conversations
     */
    public ConversationListAdapter(Context context, ArrayList<Pair<Conversation, User>> conversationsArrayList, ConversationsViewModel viewModel) {
        super(context, R.layout.fragment_conversations, conversationsArrayList);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pair<Conversation, User> conv = (Pair<Conversation, User>) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_conversation
                    , parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.user_profile_picture);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView userLastmsg = convertView.findViewById(R.id.user_last_message);
        Picasso.get().load(conv.getSecond().getProfilePictureURI()).into(imageView);
        userName.setText(conv.getSecond().getUsername());

        String txt = "";
        Message last = conv.getFirst().lastMessage();

        if (last != null) {
            txt = last.getContent();
            String currUserID = AuthenticatorFactory.getDependency().getCurrentAuthUser().getId();
            if (last.getSender().equals(currUserID)) {
                txt = "You : " + txt;
            }
        }
        userLastmsg.setText(txt);
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
