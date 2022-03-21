package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ProfileEditingActivity;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class AccountFragmentTest {

    private FragmentScenario controller;
    @Before
    public void setUp() throws Exception {
        Intents.init();
        controller = FragmentScenario.launchInContainer(AccountFragment.class,null);

    }
    @Test
    public void opensProfile(){
        Intent intent ;
        controller.onFragment(fragment -> {
            fragment.getActivity().findViewById(R.id.profilePageButton).callOnClick();
        });

        intending(allOf(toPackage(ProfileEditingActivity.class.getName())));
    }
    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}