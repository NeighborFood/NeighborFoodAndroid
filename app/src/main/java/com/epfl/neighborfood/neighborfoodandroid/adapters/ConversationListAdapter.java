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
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.Conversation;
import com.epfl.neighborfood.neighborfoodandroid.models.Message;

import java.util.ArrayList;
import java.util.Comparator;

public class ConversationListAdapter extends ArrayAdapter {

    public ConversationListAdapter(Context context, ArrayList<Conversation> conversationsArrayList) {
        super(context, R.layout.activity_conversations, conversationsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Conversation conv = (Conversation) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_conversation
                    , parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.user_profile_picture);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView userLastmsg = convertView.findViewById(R.id.user_last_message);

        imageView.setImageResource(DummyDatabase.PROFILE_IMG_ID);
        userName.setText(conv.chatter().getUsername());

        String txt = "";
        Message last = conv.lastMessage();

        if (last != null) {
            txt = last.getContent();
            String currUserID = AuthenticatorFactory.getDependency().getCurrentUser().getId();
            if (last.getSender().getId().equals(currUserID)) {
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
