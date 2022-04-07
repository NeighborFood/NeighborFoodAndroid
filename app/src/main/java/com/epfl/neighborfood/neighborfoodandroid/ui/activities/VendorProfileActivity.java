package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class VendorProfileActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView subscribeButton;
    Boolean subscribed = false;
    ImageView notificationButton;
    Boolean notifyOn = false;
    ImageView facebookIcon,instagramIcon, twitterIcon;
    private int heart = R.drawable.empty_heart;
    private int notif = R.drawable.empty_notif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        notificationButton = findViewById(R.id.notificationId);
        subscribeButton = findViewById(R.id.SubscribeId);
        facebookIcon = findViewById(R.id.facebookId);
        instagramIcon = findViewById(R.id.instagramId);
        twitterIcon = findViewById(R.id.TwitterId);
        facebookIcon.setOnClickListener(this);
        instagramIcon.setOnClickListener(this);
        twitterIcon.setOnClickListener(this);
        subscribeButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubscribeId:
                heart= (subscribed)?R.drawable.full_heart:R.drawable.empty_heart;
                subscribeButton.setImageResource(heart);
                subscribed= !subscribed;
                break;
            case R.id.notificationId:
                notif= (notifyOn)?R.drawable.full_notif:R.drawable.empty_notif;
                notificationButton.setImageResource(notif);
                notifyOn= !notifyOn;
                break;
            case R.id.facebookId:{
                String webLinkStr = "https://www.facebook.com/gordonramsay";
                openLink(webLinkStr);
                break;
            }
            case R.id.instagramId:{
                String webLinkStr = "https://www.instagram.com/gordongram";
                openLink(webLinkStr);
                break;
            }
            case R.id.TwitterId:{
                String webLinkStr = "https://twitter.com/GordonRamsay";
                openLink(webLinkStr);
                break;
            }

        }
    }
    public void openLink(String webLinkStr){
        //TODO: try catch later
        Uri uri = Uri.parse(webLinkStr);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
