package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;


@RunWith(AndroidJUnit4.class)
public class VendorDashboardFragmentTest {

    private FragmentScenario controller;

    @Before
    public void setUp() throws Exception {
        Intents.init();
        controller = FragmentScenario.launchInContainer(VendorDashboardFragment.class,null);

    }
    @Test
    public void buttonAddMealTest(){
        intending(toPackage(PlaceMealActivity.class.getName()));
        controller.onFragment(fragment -> fragment.getActivity().findViewById(R.id.addMealButton).callOnClick());

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
