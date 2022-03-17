package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.epfl.neighborfood.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToNameActivity(View view){

        Intent intent = new Intent(this, ConversationsActivity.class);
        startActivity(intent);
    }
}
