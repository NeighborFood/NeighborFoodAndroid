package com.epfl.neighborfood.neighborfoodandroid.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.epfl.neighborfood.neighborfoodandroid.R;

public class MainFragment extends Fragment {
    private SwitchCompat userModeSwitch;


    public MainFragment() {
        super(R.layout.fragment_main);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userModeSwitch = requireActivity().findViewById(R.id.modeSwitch);
        userModeSwitch.setOnCheckedChangeListener(this::toggleView);
        updateSwitchButtonText(userModeSwitch.isChecked());

    }

    private void toggleView(CompoundButton compoundButton, boolean b) {
        getChildFragmentManager().beginTransaction().replace(R.id.MainFragmentContainerFragment, b ? VendorDashboardFragment.class : MealListFragment.class, null).commit();
        updateSwitchButtonText(b);
    }

    private void updateSwitchButtonText(boolean state) {
        userModeSwitch.setText(state ? getResources().getString(R.string.vendor_mode_title) : getResources().getString(R.string.buyer_mode_title));

    }

}
