package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PastOrdersActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.ProfileEditingActivity;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.SignUpActivity;


@RunWith(AndroidJUnit4.class)
public class AccountFragmentTest {

    private FragmentScenario controller;
    @Before
    public void setUp() throws Exception {
        Intents.init();
        controller = FragmentScenario.launchInContainer(AccountFragment.class,null);

    }
    @Test
    public void buttonOpenProfileEditingTest(){
        intending(toPackage(ProfileEditingActivity.class.getName()));
        controller.onFragment(fragment -> {
            fragment.getActivity().findViewById(R.id.profilePageButton).callOnClick();
        });
    }
    @Test
    public void buttonPastOrdersTest() {
        intending(toPackage(PastOrdersActivity.class.getName()));
        controller.onFragment(fragment -> {
            fragment.getActivity().findViewById(R.id.PastOrdersButton).callOnClick();
        });
    }
    @Test
    public void buttonDisconnectTest(){
        intending(toPackage(SignUpActivity.class.getName()));
        controller.onFragment(fragment -> {
            fragment.getActivity().findViewById(R.id.logoutButton).callOnClick();
        });
    }
    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
