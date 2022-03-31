package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class VendorProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView subscribeButton;
    Boolean subscribed = false;
    ImageView notificationButton;
    Boolean notifyOn = false;

    TextView name,rate,reviews,likes,nbrMeals,bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);
        notificationButton = findViewById(R.id.notificationId);
        subscribeButton = findViewById(R.id.SubscribeId);
        name = findViewById(R.id.NameId);
        setName("SDP fan");
        reviews = findViewById(R.id.reviewNbrId);
        setReviews(60);
        rate = findViewById(R.id.rateId);
        setRate("4.8");


        nbrMeals = findViewById(R.id.nbrMealsId);
        setNbrMeals(66);
        likes = findViewById(R.id.LikesId);
        setLikes(113);
        bio = findViewById(R.id.bioId);
        setBio("I love cooking");

        subscribeButton.setOnClickListener(this);
        notificationButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubscribeId:
                if(!subscribed){
                    subscribeButton.setImageResource(R.drawable.full_heart);
                    subscribed = true;
                }
                else{
                    subscribeButton.setImageResource(R.drawable.empty_heart);
                    subscribed = false;
                }
                break;
            case R.id.notificationId:
                if(!notifyOn){
                    notificationButton.setImageResource(R.drawable.full_notif);
                    notifyOn = true;
                }
                else{
                    notificationButton.setImageResource(R.drawable.empty_notif);
                    notifyOn = false;
                }
        }
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setLikes(int likes) {
        this.likes.setText(Integer.toString(likes));
    }

    public void setBio(String bio) {
        this.bio.setText(bio);
    }

    public void setRate(String rate) {
        this.rate.setText(rate);
    }

    public void setReviews(int reviews) {
        this.reviews.setText(Integer.toString(reviews)+" reviews");
    }

    public void setNbrMeals(int nbrMeals) {
        this.nbrMeals.setText(Integer.toString(nbrMeals));
    }
}
