package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.R;

/**
 * Activity displays the profile of the vendor with all his details.
 *
 */
public class VendorProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView subscribeButton;
    private Boolean subscribed = false;
    private ImageView notificationButton;
    private Boolean notifyOn = false;
    private int heart = R.drawable.empty_heart;
    private int notif = R.drawable.empty_notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        notificationButton = findViewById(R.id.notificationId);
        subscribeButton = findViewById(R.id.SubscribeId);
        subscribeButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
        findViewById(R.id.facebookId).setOnClickListener(this);
        findViewById(R.id.instagramId).setOnClickListener(this);
        findViewById(R.id.TwitterId).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubscribeId:
                subscribed = !subscribed;
                heart = (subscribed) ? R.drawable.full_heart : R.drawable.empty_heart;
                subscribeButton.setImageResource(heart);
                subscribeButton.setTag(heart);
                break;
            case R.id.notificationId:
                notifyOn = !notifyOn;
                notif = (notifyOn) ? R.drawable.full_notif : R.drawable.empty_notif;
                notificationButton.setImageResource(notif);
                notificationButton.setTag(notif);
                break;
            case R.id.facebookId: {
                String webLinkStr = getString(R.string.FacebookLink) + "gordonramsay";
                openLink(webLinkStr);
                break;
            }
            case R.id.instagramId: {
                String webLinkStr = getString(R.string.InstagramLink) + "gordongram";
                openLink(webLinkStr);
                break;
            }
            case R.id.TwitterId: {
                String webLinkStr = getString(R.string.twitterLink) + "GordonRamsay";
                openLink(webLinkStr);
                break;
            }

        }
    }

    /**
     * loads a link in browser.
     * @param webLinkStr
     */
    public void openLink(String webLinkStr) {
        Uri uri = Uri.parse(webLinkStr);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
