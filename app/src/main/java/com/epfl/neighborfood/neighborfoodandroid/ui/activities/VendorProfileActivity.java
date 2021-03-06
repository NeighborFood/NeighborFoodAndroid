package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;

import com.epfl.neighborfood.neighborfoodandroid.NeighborFoodApplication;
import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.VendorProfileViewModel;
import com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels.factories.NeighborFoodViewModelFactory;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * Activity that displays for the user the profile of the vendor with all his details.
 * Users can subscribe to the vendor,set notifications on and access their social media links.
 */
public class VendorProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private enum SocialLinkType {
        FACEBOOK("facebook", R.drawable.facebook), INSTAGRAM("instagram", R.drawable.instagram), TWITTER("twitter", R.drawable.twitter), DEFAULT("", R.drawable.link);
        private final String domainName;
        private final int resourceID;

        SocialLinkType(String domainName, int resourceID) {
            this.domainName = domainName;
            this.resourceID = resourceID;
        }

        /**
         * Function to see if the social link type matches the given url
         *
         * @param url the url to match
         * @return true if the link matches the social link type
         */
        public boolean matches(String url) {
            return url.contains(domainName);
        }

        public static SocialLinkType getSocialLinkType(String url) {
            for (SocialLinkType s : SocialLinkType.values()) {
                if (s.ordinal() != SocialLinkType.DEFAULT.ordinal() && s.matches(url)) {
                    return s;
                }
            }
            return SocialLinkType.DEFAULT;
        }
    }

    private final static int LINK_IMAGE_WIDTH = 50;
    private final static int LINK_IMAGE_HEIGHT = 50;
    private ImageView notificationButton;
    private User vendor;
    private VendorProfileViewModel vmodel;
    private boolean subscriptionTaskComplete;
    private boolean isCurrentUserSubscribed;
    private boolean notificationButtonState;
    private GridLayout linksGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        Toolbar toolbar = findViewById(R.id.vendorProfileToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        notificationButton = findViewById(R.id.notificationId);
        notificationButton.setOnClickListener(this);
        findViewById(R.id.messageVendor).setOnClickListener(this);
        linksGridLayout = findViewById(R.id.SocialLinksGridLayout);
        vmodel = new ViewModelProvider(this, new NeighborFoodViewModelFactory((NeighborFoodApplication) this.getApplication())).get(VendorProfileViewModel.class);
        String vendorID = getUserIDFromIntent();
        if (vendorID != null) {
            vmodel.getUserByID(vendorID).addOnSuccessListener(user -> {
                this.vendor = user;
                updateUI();
            });
        }
        subscriptionTaskComplete = true;
    }

    private void updateLinks() {
        linksGridLayout.removeAllViews();
        for (String s : vendor.getLinks()) {
            addLinkButton(s);
        }

    }

    private void addLinkButton(String url) {
        ImageView image = new ImageView(this);
        SocialLinkType linkType = SocialLinkType.getSocialLinkType(url);
        image.setImageResource(linkType.resourceID);
        linksGridLayout.addView(image);
        image.setOnClickListener(v -> openLink(url));
        image.getLayoutParams().width = LINK_IMAGE_WIDTH;
        image.getLayoutParams().height = LINK_IMAGE_HEIGHT;
    }

    private void updateUI() {
        if (vendor == null) {
            return;
        }
        ((TextView) findViewById(R.id.NameId)).setText(String.format("%s %s", vendor.getFirstName(), vendor.getLastName()));
        isCurrentUserSubscribed = vmodel.getCurrentUser().getSubscribedIDs().contains(vendor.getId());
        updateNotificationIcon(isCurrentUserSubscribed);
        updateNumberOfLikes();
        ((TextView) findViewById(R.id.bioValue2)).setText(vendor.getBio());
        Picasso.get().load(vendor.getProfilePictureURI()).into((ImageView) findViewById(R.id.ProfilePictureId));
        updateLinks();
    }

    private String getUserIDFromIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString("userID");
        }
        return null;
    }

    private void updateNotificationIcon(boolean state) {
        int notif = state ? R.drawable.full_notif : R.drawable.empty_notif;
        notificationButton.setImageResource(notif);
        notificationButton.setTag(notif);
        notificationButtonState = state;
    }

    private void updateNumberOfLikes() {
        ((TextView) findViewById(R.id.LikesId)).setText(String.valueOf(vendor.getNumberSubscribers()));
    }

    private void subscriptionTaskComplete(boolean subscriptionState) {
        isCurrentUserSubscribed = subscriptionState;
        Toast.makeText(this, getResources().getString(subscriptionState ? R.string.subscribe_success : R.string.unsubscribe_success, vendor.getUsername()), Toast.LENGTH_SHORT).show();
        updateNotificationIcon(subscriptionState);
        updateNumberOfLikes();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notificationId:
                notificationButtonState = !notificationButtonState;
                updateNotificationIcon(notificationButtonState);
                if (!subscriptionTaskComplete) {
                    break;
                }
                subscriptionTaskComplete = false;
                if (isCurrentUserSubscribed) {
                    vmodel.unsubscribeFromVendor(vendor).addOnCompleteListener(t -> subscriptionTaskComplete = true).addOnSuccessListener((a) -> subscriptionTaskComplete(false));
                } else {
                    vmodel.subscribeToVendor(vendor).addOnCompleteListener(t -> subscriptionTaskComplete = true).addOnSuccessListener((a) -> subscriptionTaskComplete(true));
                }
                break;

            case R.id.messageVendor:
                Intent intent = new Intent(this, ChatRoomActivity.class);
                String conversationID = vmodel.getCurrentUser().getId().compareTo(vendor.getId()) > 0 ? vmodel.getCurrentUser().getId() + "-" + vendor.getId() : vendor.getId() + "-" + vmodel.getCurrentUser().getId();
                intent.putExtra("ConversationID", conversationID);
                startActivity(intent);
        }
    }

    /**
     * loads a link in browser.
     *
     * @param webLinkStr the web link string
     */
    public void openLink(String webLinkStr) {
        Uri uri = Uri.parse(webLinkStr);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // tool bar Back Icon
        {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
