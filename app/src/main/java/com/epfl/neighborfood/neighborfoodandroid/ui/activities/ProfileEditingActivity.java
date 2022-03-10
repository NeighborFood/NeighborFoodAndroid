package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class ProfileEditingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ppView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileEditToolbar);
        setSupportActionBar(toolbar);
        ppView = (ImageView) findViewById(R.id.profilePictureImageView);
        ppView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profilePictureImageView:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,1);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 1 && resultCode ==RESULT_OK && data!= null){
            ppView.setImageURI(data.getData());
        }

    }
}