package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.graphics.drawable.Drawable;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        notificationButton = findViewById(R.id.notificationId);
        subscribeButton = findViewById(R.id.SubscribeId);
        subscribeButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubscribeId:
                if(!subscribed){
                    subscribeButton.setImageResource(R.drawable.full_heart);
                    subscribeButton.setTag(R.drawable.full_heart);
                    subscribed = true;
                }
                else{
                    subscribeButton.setImageResource(R.drawable.empty_heart);
                    subscribeButton.setTag(R.drawable.empty_heart);
                    subscribed = false;
                }
                break;
            case R.id.notificationId:
                if(!notifyOn){
                    notificationButton.setImageResource(R.drawable.full_notif);
                    notificationButton.setTag(R.drawable.full_notif);
                    notifyOn = true;
                }
                else{
                    notificationButton.setImageResource(R.drawable.empty_notif);
                    notificationButton.setTag(R.drawable.empty_notif);
                    notifyOn = false;
                }
        }
    }
}
